package de.dhbwka.java.exercise.control;

import java.util.Scanner;

public class AddUp {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int input = 0;
        int sum = 0;

        System.out.println("While: ");
        while (input >= 0) {
            sum += input;
            System.out.print("Enter number (<0 to end): ");
            input = scan.nextInt();
        };

        System.out.println("Sum: " + sum);
        System.out.println();

        System.out.println("Do-While: ");
        input = 0;
        sum = 0;
        do {
            sum += input;
            System.out.print("Enter number (<0 to end): ");
            input = scan.nextInt();
        } while (input >= 0);

        System.out.println("Sum: "+sum);

        scan.close();
    }
}
