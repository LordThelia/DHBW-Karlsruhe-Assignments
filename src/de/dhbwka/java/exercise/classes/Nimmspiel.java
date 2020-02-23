package de.dhbwka.java.exercise.classes;

public class Nimmspiel {
    String[] playerNames = new String[2];
    int[] stacks = new int[2];
    java.util.Scanner sc;

    public Nimmspiel(String spieler1, String spieler2) {
        playerNames[0] = spieler1;
        playerNames[1] = spieler2;
        stacks[0] = (int) (Math.random() * 10) + 5;
        stacks[1] = (int) (Math.random() * 10) + 5;

        sc = new java.util.Scanner(System.in);
    }

    public void play() {
        int player = 1;
        do {
            player = player == 0 ? 1 : 0;

            doPlayerMove(player);
        } while (!isStackEmpty());

        System.out.println("Game endet");
        System.out.println("Winner: " + playerNames[player] + "!");
    }

    private void doPlayerMove(int player) {
        int stack;
        int amountDrawn;

        System.out.print("Player " + playerNames[player] + ": From which Stack? ");
        stack = sc.nextInt() - 1;

        System.out.println("Player" + playerNames[player] + ": How many? ");
        amountDrawn = sc.nextInt();

        stacks[stack] -= amountDrawn;
        System.out.println(this);
    }

    private boolean isStackEmpty() {
        return (stacks[0] == 0) && (stacks[1] == 0);
    }

    public String toString() {
        return "Player: " + playerNames[0] + ": " + stacks[0] + "\nPlayer: " + playerNames[1] + ": " + stacks[1];
    }
}
