package de.dhbwka.java.exercise.arrays;

import java.util.Scanner;

public class DotProduct {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("How many elements? ");
        int quantity = input.nextInt();

        int[] vec_x = new int[quantity];
        int[] vec_y = new int[quantity];
        int cartesian = 0;

        for (int i = 0; i < quantity; ++i) {
            System.out.print("x" + i + ": ");
            vec_x[i] = input.nextInt();

            System.out.print("y" + i + ": ");
            vec_y[i] = input.nextInt();

            cartesian += vec_x[i] * vec_y[i];
        }
        System.out.println("The scalar product of x and y is: " + cartesian);

    }
}
