package de.dhbwka.java.exercise.ui.event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class ShellGame extends JFrame {
    private int randomNumber, attempts;
    private JTextField tfName, tfResult;

    public ShellGame() {
        JPanel pEntries = new JPanel();
        pEntries.setLayout(new FlowLayout());

        pEntries.add(new JLabel("Player Name"));
        this.tfName = new JTextField("Name", 10);
        pEntries.add(this.tfName);

        JPanel pButtons = new JPanel();
        pButtons.setLayout(new GridLayout(1,3));

        for(int i = 1; i <= 3; ++i) {
            GuessingButton btn = new GuessingButton("Shell " + i, i);
            pButtons.add(btn);
            btn.addActionListener(this::shellGuessed);
        }

        JPanel sButtons = new JPanel();
        sButtons.setLayout(new GridLayout(1,2));
        JButton btnStatistics = new JButton("Statistics");
        btnStatistics.addActionListener(this::getBestPlayer);

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(f -> System.exit(0));

        sButtons.add(btnStatistics);
        sButtons.add(btnExit);

        this.tfResult = new JTextField("", 30);

        this.setLayout(new FlowLayout());
        this.add(pEntries);
        this.add(pButtons);
        this.add(sButtons);
        this.add(this.tfResult);

        this.setTitle("Shell Game");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        placeRandomBall();
        this.attempts = 0;
    }

    private void shellGuessed(ActionEvent event) {
        int guess = ((GuessingButton) event.getSource()).getNumber();

        ++this.attempts;

        if(guess == this.randomNumber) {
            this.tfResult.setText("Attempt " + this.attempts + " wins: Ball was below shell " + this.randomNumber);
            addPlayerToBestList();
        } else {
            this.tfResult.setText("Attempt " + this.attempts + " loses: Ball was below shell " + this.randomNumber + " try again!");
            placeRandomBall();
        }
    }

    private void getBestPlayer(ActionEvent event) {
        File leaderboard = new File("shellgame.txt");

        int row = 0;
        int attempts = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(leaderboard))) {
            while(br.ready()) {
                String[] entry = br.readLine().split(",");

                if(entry.length != 2) {
                    continue;
                }

                try {
                    attempts = Integer.parseInt(entry[0]);
                } catch (NumberFormatException e) {
                    continue;
                }

                ++row;
            }
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Could not read leaderboard! \n\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.tfResult.setText("Successfull after: " + (attempts / row) + "attempts!");
    }

    private void addPlayerToBestList() {
        File leaderboard = new File("shellgame.txt");

        String playerName = this.tfName.getText().replaceAll(",", "");

        try(PrintWriter pw = new PrintWriter(new FileWriter(leaderboard, true))) {
            pw.println("" + this.attempts + "," + playerName);
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Could not save to leaderboard! \n\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void placeRandomBall() {
        this.randomNumber = (int) (Math.random() * 3) + 1;
    }

    public static void main(String[] args) {
        new ShellGame();
    }
}

class GuessingButton extends JButton {
    private int number;

    public GuessingButton(String title, int number) {
        super(title);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}