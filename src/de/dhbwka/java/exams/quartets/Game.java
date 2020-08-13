package de.dhbwka.java.exams.quartets;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game implements Runnable {
    private ArrayList<Category> categories = new ArrayList<Category>();
    private QuartetsClient player1;
    private QuartetsClient player2;
    private QuartetsClient activePlayer;
    private int rounds = 0;
    private boolean isRunning = true;

    public Game(List<Category> categoryPool, int amount) throws QuartetsException {
        if(amount > categoryPool.size()) {
            throw new QuartetsException("Too few categories available");
        }

        Random random = new Random();

        while(this.categories.size() < amount) {
            Category cat = categoryPool.get(random.nextInt(categoryPool.size()));
            if(!this.categories.contains(cat)) {
                this.categories.add(cat);
            }
        }
    }

    public void registerClient1(QuartetsClient client) {
        this.player1 = client;
    }

    public void registerClient2(QuartetsClient client) {
        this.player2 = client;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    @Override
    public void run() {
        int seconds = 0;
        while(this.isRunning) {
            try {
                this.player1.setPlayedSeconds(seconds);
                this.player2.setPlayedSeconds(seconds);
                ++seconds;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() throws QuartetsException {
        if(this.player1 == null || this.player2 == null) {
            throw new QuartetsException("Both players are required!");
        }

        this.player1.showTopCard(true);
        this.player2.showTopCard(false);
        this.activePlayer = player1;
    }

    public void categorySelected(Category cat) {
        ++this.rounds;

        Card p1Card = this.player1.getPlayer().getTopCard();
        Card p2Card = this.player2.getPlayer().getTopCard();
        int comparision = p1Card.compareTo(cat, p2Card);

        if(comparision == 0) {
            this.player1.getPlayer().addCard(this.player1.getPlayer().drawTopCard());
            this.player2.getPlayer().addCard(this.player2.getPlayer().drawTopCard());

            this.player1.showTopCard((this.activePlayer == this.player1 ? true : false));
            this.player2.showTopCard((this.activePlayer == this.player1 ? false : true));
        } else if(comparision < 0) {
            this.player2.getPlayer().addCard(this.player2.getPlayer().drawTopCard());
            this.player2.getPlayer().addCard(this.player1.getPlayer().drawTopCard());

            this.player1.showTopCard(false);
            this.player2.showTopCard(true);
            this.activePlayer = player2;
        } else {
            this.player1.getPlayer().addCard(this.player1.getPlayer().drawTopCard());
            this.player1.getPlayer().addCard(this.player2.getPlayer().drawTopCard());

            this.player1.showTopCard(true);
            this.player2.showTopCard(false);
            this.activePlayer = player1;
        }

        if(!this.player1.getPlayer().hasCards() || !this.player2.getPlayer().hasCards()) {
            String winnerText = this.player1.getPlayer().getName() + " won in " + this.rounds + " rounds";

            if(this.player2.getPlayer().hasCards()) {
                winnerText = this.player2.getPlayer().getName() + " won in " + this.rounds + " rounds";
            }

            JOptionPane.showMessageDialog(null, winnerText);
            addPlayerHighscore(winnerText);

            this.isRunning = false;

            this.player1.showTopCard(false);
            this.player2.showTopCard(false);
        }
    }

    private void addPlayerHighscore(String text) {
        try(PrintWriter pw = new PrintWriter(new FileWriter("highscore.txt", true))) {
            pw.println(text);
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Could not save to highscore! \n\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
