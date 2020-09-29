package de.dhbwka.java.exams.stadtLandFluss;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Game implements Runnable {
    private List<Sheet> sheets = new ArrayList<>();
    private List<ColumnType> columns;
    private List<String> validWords = new ArrayList<>();
    private char firstChar;
    private int max = 3;
    private int min = 3;
    private int seconds;
    private boolean isRunning = false;
    private int timeLeft = 0;

    public Game(int min, int max, int seconds) {
        if(min >= 3 && max >= min) {
            this.max = max;
            this.min = min;
        }

        this.seconds = seconds;
        loadWordList();
    }

    public void register(Sheet sheet) {
        this.sheets.add(sheet);
    }

    public char createFirstCharacter() {
        Random r = new Random();
        return (char) (r.nextInt(26) + 'a');
    }

    public void startGame() {
        if(!this.isRunning) {
            this.isRunning = true;

            this.firstChar = createFirstCharacter();
            this.columns = createColumns();
            this.timeLeft = this.seconds;

            for(Sheet s : this.sheets) {
                s.startGame();
                s.getPlayer().setPoints(0);
            }

            Thread runner = new Thread(this);
            runner.start();
        }
    }

    public void stopGame() {
        this.isRunning = false;

        for(Sheet s : this.sheets) {
            s.stopGame();
        }

        List<Integer>[] playerPoints = new ArrayList[this.sheets.size()];

        for(int catIndex = 0; catIndex < this.columns.size(); ++catIndex) {
            String[] answers = new String[this.sheets.size()];

            for(int playerIndex = 0; playerIndex < this.sheets.size(); ++playerIndex) {
                if(playerPoints[playerIndex] == null) {
                    playerPoints[playerIndex] = new ArrayList<>();
                }

                String answer = this.sheets.get(playerIndex).getAnswer(catIndex);

                if(!answer.isBlank() && isWordCorrect(answer, this.columns.get(catIndex))) {
                    answers[playerIndex] = answer;
                } else {
                    answers[playerIndex] = null;
                }

                playerPoints[playerIndex].add(0);
            }

            for(int playerIndex = 0; playerIndex < this.sheets.size(); ++playerIndex) {
                String answer = answers[playerIndex];
                boolean isSimilar = false;
                boolean isAllNull = true;

                if(answer == null) {
                    continue;
                }

                for (int answerIndex = 0; answerIndex < answers.length; ++answerIndex) {
                    if(playerIndex != answerIndex) {
                        if(answers[answerIndex] != null) {
                            isAllNull = false;

                            if(answers[answerIndex].equalsIgnoreCase(answer)) {
                                isSimilar = true;
                                break;
                            }
                        }
                    }
                }

                if(isAllNull) {
                    playerPoints[playerIndex].set(catIndex, 20);
                } else if(isSimilar) {
                    playerPoints[playerIndex].set(catIndex, 5);
                } else {
                    playerPoints[playerIndex].set(catIndex, 10);
                }
            }
        }

        for(int playerIndex = 0; playerIndex < this.sheets.size(); ++playerIndex) {
            this.sheets.get(playerIndex).getPlayer().setPoints(playerPoints[playerIndex].stream().mapToInt(a -> a).sum());
            this.sheets.get(playerIndex).setPoints(playerPoints[playerIndex]);
        }
    }

    private boolean isWordCorrect(String word, ColumnType col) {
        word = word.toLowerCase();

        if(word.charAt(0) != this.firstChar) {
            return false;
        }

        if(this.validWords.contains(word)) {
            return true;
        }

        int confirmation = JOptionPane.showConfirmDialog(null, "Ist '" + word + "' korrekt für die Kategorie '" + col.getTitle() + "'?");

        if(confirmation == JOptionPane.YES_OPTION) {
            this.validWords.add(word);

            try(BufferedWriter bw = new BufferedWriter(new FileWriter("validwords.txt", true))) {
                bw.write(word);
                bw.newLine();
            } catch (IOException e) {}
        }

        return confirmation == JOptionPane.YES_OPTION;
    }

    private void loadWordList() {
        try (BufferedReader br = new BufferedReader(new FileReader("validwords.txt"))) {
            while (br.ready()) {
                this.validWords.add(br.readLine());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error loading questions", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<ColumnType> createColumns() {
        int columnCount = ThreadLocalRandom.current().nextInt(this.min, this.max + 1);

        List<ColumnType> columns = new ArrayList<>();
        columns.add(ColumnType.CITY);
        columns.add(ColumnType.COUNTRY);
        columns.add(ColumnType.RIVER);

        Random rand = new Random();
        while(columns.size() < columnCount) {
            int column = rand.nextInt(columnCount);

            if (!columns.contains(ColumnType.values()[column])) {
                columns.add(ColumnType.values()[column]);
            }
        }

        return columns;
    }

    @Override
    public void run() {
        while(this.isRunning) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}

            --this.timeLeft;

            for(Sheet s : this.sheets) {
                s.refreshTop();
            }

            if(this.timeLeft == 0) {
                stopGame();
            }
        }
    }

    public char getFirstChar() {
        return firstChar;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public List<ColumnType> getColumns() {
        return columns;
    }
}
