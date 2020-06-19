package de.dhbwka.java.exercise.ui.event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class NumberGuess extends JFrame {
    private int randomNumber, attempts;
    private JTextField tfName, tfNumber, tfInfo;
    private JButton btnNewGame, btnOK, btnBestPlayer;

    public NumberGuess() {
        JPanel pEntries = new JPanel();
        pEntries.setLayout(new GridLayout(2,2));

        pEntries.add(new JLabel("Player Name"));
        this.tfName = new JTextField("Name");
        pEntries.add(this.tfName);

        pEntries.add(new JLabel("Enter number between 1 and 1000"));
        this.tfNumber = new JTextField();
        pEntries.add(this.tfNumber);

        JPanel pButtons = new JPanel();
        pButtons.setLayout(new GridLayout(1,4));

        this.btnNewGame = new JButton("New Game");
        this.btnNewGame.addActionListener(this::newGame);

        this.btnOK = new JButton("OK");
        this.btnOK.setEnabled(false);
        this.btnOK.addActionListener(this::makeGuess);

        this.btnBestPlayer = new JButton("Best Player");
        this.btnBestPlayer.addActionListener(this::getBestPlayer);

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(f -> System.exit(0));

        pButtons.add(btnNewGame);
        pButtons.add(btnOK);
        pButtons.add(btnBestPlayer);
        pButtons.add(btnExit);

        this.tfInfo = new JTextField();

        this.setLayout(new BorderLayout(0,20));
        this.add(pEntries, BorderLayout.NORTH);
        this.add(pButtons, BorderLayout.CENTER);
        this.add(tfInfo, BorderLayout.SOUTH);

        this.setTitle("Number Guessing Game");
        this.setSize(500, 200);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void newGame(ActionEvent event) {
        this.btnNewGame.setEnabled(false);
        this.btnBestPlayer.setEnabled(false);
        this.btnOK.setEnabled(true);
        this.tfNumber.setText("");
        this.tfInfo.setText("");
        this.randomNumber = (int) (Math.random() * 1000);
        this.attempts = 0;
    }

    private void makeGuess(ActionEvent event) {
        int guess = 0;
        try {
            guess = Integer.parseInt(this.tfNumber.getText());
        } catch (NumberFormatException e) {
            this.tfNumber.setText("");
            this.tfInfo.setText("Invalid Input");
            return;
        }

        ++this.attempts;

        if(guess > this.randomNumber) {
            this.tfInfo.setText("Attempt #" + this.attempts + ": " + guess + " => too big!");
        } else if(guess < this.randomNumber) {
            this.tfInfo.setText("Attempt #" + this.attempts + ": " + guess + " => too small!");
        } else {
            this.tfInfo.setText("Found! " + this.randomNumber + " in " + this.attempts + " attempts!");

            addPlayerToBestList();

            this.btnOK.setEnabled(false);
            this.btnNewGame.setEnabled(true);
            this.btnBestPlayer.setEnabled(true);
        }

        this.tfNumber.setText("");
    }

    private void getBestPlayer(ActionEvent event) {
        File leaderboard = new File("bestGuesses.txt");

        int row = 0;
        int attempt = 0;
        int lowestAttempt = 0;
        String playerName = "";

        try(BufferedReader br = new BufferedReader(new FileReader(leaderboard))) {
            while(br.ready()) {
                String[] entry = br.readLine().split(",");

                if(entry.length != 2) {
                    continue;
                }

                try {
                    attempt = Integer.parseInt(entry[0]);
                } catch (NumberFormatException e) {
                    continue;
                }

                if(row == 0) {
                    lowestAttempt = attempt;
                    playerName = entry[1];
                }

                if(attempt < lowestAttempt) {
                    lowestAttempt = attempt;
                    playerName = entry[1];
                }

                ++row;
            }
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Could not read leaderboard! \n\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.tfInfo.setText("Best Player: " + playerName + " with " + lowestAttempt + " attempts!");
    }

    private void addPlayerToBestList() {
        File leaderboard = new File("bestGuesses.txt");

        String playerName = this.tfName.getText().replaceAll(",", "");

        try(PrintWriter pw = new PrintWriter(new FileWriter(leaderboard, true))) {
            pw.println("" + this.attempts + "," + playerName);
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Could not save to leaderboard! \n\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new NumberGuess();
    }
}
