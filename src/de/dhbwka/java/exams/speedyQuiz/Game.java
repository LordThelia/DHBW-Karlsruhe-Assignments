package de.dhbwka.java.exams.speedyQuiz;

import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Game implements Runnable {
    private ArrayList<Question> questions = new ArrayList<Question>();
    private ArrayList<GameClient> clients = new ArrayList<GameClient>();
    private boolean isGameRunning = false;
    private boolean hasPlayerAnswered = false;
    private int questionIndex = 0;
    private long startTime;
    private int remainingSeconds = 10;
    private Thread bgThread;

    public Game(List<Question> questions, int numberOfQuestions) throws GameException {
        Random rand = new Random();

        if(numberOfQuestions > questions.size()) {
            throw new GameException("Too few questions available");
        }

        while(this.questions.size() < numberOfQuestions) {
            int questionIndex = rand.nextInt(questions.size());

            if(!this.questions.contains(questions.get(questionIndex))) {
                this.questions.add(questions.get(questionIndex));
            }
        }
    }

    public void registerClient(GameClient client) {
        if(!this.isGameRunning) {
            this.clients.add(client);
        }
    }

    public int getQuestionsCount() {
        return this.questions.size();
    }

    public void startGame() {
        this.isGameRunning = true;

        for(GameClient client : this.clients) {
            client.setQuestion(this.questionIndex, this.questions.get(this.questionIndex));
        }

        this.startTime = new Date().getTime();
        this.bgThread = new Thread(this);
        this.bgThread.start();
    }

    public void answerSelected(GameClient client, int index) {
        this.hasPlayerAnswered = true;
        for(GameClient c : this.clients) {
            if(c.equals(client)) {
                if(this.questions.get(this.questionIndex).getCorrectIndex() == index) {
                    c.setAnswerState(this.questionIndex, Status.CORRECT);
                } else {
                    c.setAnswerState(this.questionIndex, Status.WRONG);
                }
            } else {
                c.setAnswerState(this.questionIndex, Status.NO_ANSWER);
            }
        }

        nextQuestion();
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    private void nextQuestion() {
        this.hasPlayerAnswered = false;
        ++this.questionIndex;

        if(this.questionIndex < this.questions.size()) {
            for(GameClient client : this.clients) {
                client.setQuestion(this.questionIndex, this.questions.get(this.questionIndex));
            }

            this.remainingSeconds = 10;
        } else {
            this.isGameRunning = false;

            int time = (int) ((new Date().getTime() - startTime) / 1000);

            StringBuilder result = new StringBuilder();
            result.append("Game finished after ");
            result.append(time);
            result.append("seconds, scores: ");

            for(GameClient client : this.clients) {
                client.gameIsOver();
                result.append(", ");
                result.append(client.getPlayerName());
                result.append(" (");
                result.append(client.getPoints());
                result.append(")");
            }

            JOptionPane.showMessageDialog(null, result.toString());

            result.append("\n");

            try (BufferedWriter br = new BufferedWriter(new FileWriter("highscore.txt", true))) {
                br.write(result.toString());
            } catch (IOException e) {}
        }
    }

    @Override
    public void run() {
        while(this.isGameRunning) {
            while(!this.hasPlayerAnswered && this.isGameRunning && this.remainingSeconds > 0) {
                --this.remainingSeconds;

                for(GameClient client : this.clients) {
                    client.setRemainingSeconds(this.remainingSeconds);
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {}
            }

            if(!this.hasPlayerAnswered && this.isGameRunning) {
                for(GameClient client : this.clients) {
                    client.setAnswerState(this.questionIndex, Status.NO_ANSWER);
                }

                nextQuestion();
            }
        }
    }
}
