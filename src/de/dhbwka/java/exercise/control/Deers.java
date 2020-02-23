package de.dhbwka.java.exercise.control;

public class Deers {
    public static void main(String[] args) {
        int deers = 200;
        int year = 1;

        while(deers < 300) {
            deers = (int) ((deers * 1.1) - 15);

            System.out.println(year + ": " + deers);

            ++year;
        }
    }
}
