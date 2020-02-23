package de.dhbwka.java.exercise.control;

public class TemperatureTable {
    public static void main(String[] args) {
        System.out.println("Fahrenheit | Celsius");
        System.out.println("-----------+--------");

        for (int i = 0; i <= 300; i += 20) {
            double c = ((i - 32) * 5) / 9.0;

            System.out.printf("%10d | %3.2f \n", i, c);
        }
    }
}
