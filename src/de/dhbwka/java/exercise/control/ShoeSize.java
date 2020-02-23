package de.dhbwka.java.exercise.control;

public class ShoeSize {
    public static void main(String[] args) {
        System.out.println("Zentimeter    | Größe");
        System.out.println("--------------+------");

        for (int i = 30; i <= 50; ++i) {
            double centi = i / 1.5;
            System.out.printf("%2.2f - %2.2f | %d\n", (centi - 2/3.0), centi, i);
        }
    }
}
