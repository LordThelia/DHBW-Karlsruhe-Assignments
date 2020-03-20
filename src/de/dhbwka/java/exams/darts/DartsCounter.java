package de.dhbwka.java.exams.darts;

public class DartsCounter {
    public static void main( String[] args ) {
        final Board b = new Board();

        final Player[] players = new Player[] {new Player("Michael van Gerwen"), new Player("Rob Cross")};

        final Game g = new Game(b, players);
        g.start();
    }
}
