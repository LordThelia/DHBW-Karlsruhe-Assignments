package de.dhbwka.java.exams.darts;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private Board board;
    private Player[] players;
    private HashMap<Integer, String> checkouts;

    public Game(Board board, Player[] players) {
        this.board = board;
        this.players = players;
        this.checkouts = new HashMap<Integer, String>();
    }

    public void start() {
        boolean isFinished = false;
        boolean hasPlayerZeroPoints = false;
        boolean hasTenRoundsPlayed = false;
        int player = 1;

        readCheckoutCombinations();
        Scanner sc = new Scanner(System.in);

        do {
            player = player == 0 ? 1 : 0;
            int remainingPoints = this.players[player].getRemainingPoints();
            System.out.println("Player: " + this.players[player].getName() + ", " + remainingPoints + " points remaining.");

            if(remainingPoints <= 170) {
                System.out.println("Possible finish combination: " + this.checkouts.get(remainingPoints));
            }

            System.out.print("Enter visit: ");
            System.out.flush();
            String playerThrow = sc.nextLine();
            String[] fieldsHitted = playerThrow.split( " " );
            Field[] fields = new Field[fieldsHitted.length];

            for(int i = 0; i < fields.length; ++i) {
                fields[i] = this.board.parseField(fieldsHitted[i]);
            }
            Visit playerVisit = new Visit(fields);
            this.players[player].addVisit(playerVisit);
            System.out.println("Scored: " + playerVisit.getValue());

            hasPlayerZeroPoints = players[0].getRemainingPoints() == 0 || players[1].getRemainingPoints() == 0;
            hasTenRoundsPlayed = players[0].getValidVisitsLength() == 10 && players[1].getValidVisitsLength() == 10;
            isFinished = hasPlayerZeroPoints || hasTenRoundsPlayed;
        } while(!isFinished);

        if(hasPlayerZeroPoints) {
            System.out.println("Game shot and the leg, " + this.players[player].getName());

            //Highscore
            File path = new File(System.getProperty("user.home") + "\\Documents\\Java");
            path.mkdir();

            try (PrintWriter pw = new PrintWriter(new FileWriter(path + "\\highscore.txt",true))) {
                pw.println(this.players[player].getName() + " won with " + this.players[player].getCountDartsThrown() + " darts.");
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        if(hasTenRoundsPlayed) {
            System.out.println("You're too bad for this game!");
        }

        sc.close();
    }

    private void readCheckoutCombinations() {
        File checkouts = new File("checkouts.txt");

        if(checkouts.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(checkouts))) {
                int row = 1;
                while (br.ready()) {
                    this.checkouts.put(row, br.readLine());
                    ++row;
                }
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
