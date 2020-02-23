package de.dhbwka.java.exercise.classes;

import java.util.Scanner;

public class MasterMind {
    final int MAX_ATTEMPTS = 20;
    char[] cpuComb;
    char[][] playerComb;
    int[] correct;
    int[] incorrect;
    Scanner sc;
    int attempt;

    public MasterMind() {
        cpuComb     = new char[5];
        playerComb  = new char[MAX_ATTEMPTS][5];
        correct     = new int[MAX_ATTEMPTS];
        incorrect   = new int[MAX_ATTEMPTS];

        sc = new Scanner(System.in);
    }

    public void play() {
        createCpuCombination();
        playerLoop();
    }

    private void createCpuCombination() {
        for (int i = 0; i < 5; ++i) {
            cpuComb[i] = (char) ('A' + (int) (Math.random() * 8));
        }

        for(int i = 0; i < 5; ++i) {
            System.out.print(cpuComb[i]);
        }
        System.out.println();
    }

    private void playerLoop() {
        for(attempt = 0; attempt < MAX_ATTEMPTS; ++attempt) {
            getPlayerGuess();
            checkGuess();

            if(correct[attempt] == 5) {
                System.out.println("Won with " + (attempt + 1) + " attempts!");
                break;
            }
        }

        if(attempt == MAX_ATTEMPTS) {
            System.out.println("You lost!");
        }
    }

    private void getPlayerGuess() {
        boolean correct;

        do {
            correct = true;
            System.out.print("Tip: ");
            String str = sc.next();

            if(str.length() == 5) {
                for(int i = 0; i < 5; ++i) {
                    playerComb[attempt][i] = str.toUpperCase().charAt(i);

                    if(playerComb[attempt][i] < 'A' || playerComb[attempt][i] > 'H') {
                        correct = false;
                        System.out.println("Guess contains wrong letter!");
                        break;
                    }
                }
            } else {
                correct = false;
                System.out.println("Tip must be 5 characters long!");
            }
        } while(!correct);
    }

    private void checkGuess() {
        System.out.println("Attempt: " + (attempt + 1));
        for(int i = 0; i <= attempt; ++i) {
            for (int j = 0; j < 5; ++j) {
                System.out.print(playerComb[i][j]);
            }

            calculatePoints();
            System.out.println(" - C: " + correct[attempt] + " W: " + incorrect[attempt]);
        }
    }

    private void calculatePoints() {
        correct[attempt]    = 0;
        incorrect[attempt]  = 0;

        for(char ch = 'A'; ch <='H'; ++ch) {
            int charInCPU       = 0;
            int charInPlayer    = 0;
            int charMatching    = 0;

            //CPU Combination
            for(int i = 0; i < 5; ++i) {
                if(cpuComb[i] == ch) {
                    ++charInCPU;

                    if(playerComb[attempt][i] == ch) {
                        ++charMatching;
                    }
                }
            }

            //Player guess
            for(int i = 0; i < 5; ++i) {
                if(playerComb[attempt][i] == ch) {
                    ++charInPlayer;
                }
            }

            correct[attempt]    += charMatching;
            incorrect[attempt]  += Math.min(charInPlayer, charInCPU) - charMatching;
        }
    }
}
