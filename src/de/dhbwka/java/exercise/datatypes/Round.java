package de.dhbwka.java.exercise.datatypes;

public class Round {
    public static void main(String[] args) {
        double pi = 3.1415926;
        double e  = 2.7182818;

        System.out.println("Round positiv");
        int intPI = (int) (pi + 0.5);
        int intE  = (int) (e + 0.5);
        System.out.println("Pi as int: " + intPI);
        System.out.println("E as int: " + intE);
        System.out.println();

        System.out.println("round negativ: wrong way");
        pi = - pi;
        e  = - e;
        intPI = (int) (pi - 0.5);
        intE  = (int) (e - 0.5);
        System.out.println("- Pi as int: " + intPI);
        System.out.println("- E as int: " + intE);
        System.out.println();

        System.out.println("round negativ: right way");
        intPI = (int) (pi + ((pi > 0) ? 0.5 : -0.5));
        intE  = (int) (e + ((e > 0) ? 0.5 : -0.5));
        System.out.println("- Pi as int: " + intPI);
        System.out.println("- E as int: " + intE);
        System.out.println();
    }
}
