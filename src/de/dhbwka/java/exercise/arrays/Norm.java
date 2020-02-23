package de.dhbwka.java.exercise.arrays;

import java.util.Scanner;

public class Norm {
    public static void main(String[] args) {
        double d = 0;

        Scanner input = new Scanner(System.in);
        System.out.print("How many elements? ");

        int[] elements = new int[input.nextInt()];

        for (int i = 0; i < elements.length; ++i) {
            System.out.print("x" + i + ": ");
            elements[i] = input.nextInt();
            d += Math.pow(elements[i], 2);
        }

        double values = Math.sqrt(d);
        System.out.println("The norm is " + values);

    }
}
