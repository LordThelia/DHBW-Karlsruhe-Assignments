package de.dhbwka.java.exercise.arrays;

import java.util.Scanner;

public class MatrixSubtraction {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = 0;
        int cols = 0;
        System.out.println("Zeilen: ");
        rows = sc.nextInt();

        System.out.println("Spalten: ");
        cols = sc.nextInt();

        sc.close();

        int[][] mat_1 = new int[cols][rows];
        int[][] mat_2 = new int[cols][rows];

        // fill with random numbers
        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                mat_1[i][j] = (int) (Math.random() * 100);
                mat_2[i][j] = (int) (Math.random() * 100);
            }
        }

        // print first
        System.out.println("X:");
        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                System.out.printf("%3d", mat_1[i][j]);
            }
            System.out.println();
        }

        // print second
        System.out.println("Y:");
        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                System.out.printf("%3d", mat_2[i][j]);
            }
            System.out.println();
        }

        // substract
        System.out.println("X-Y:");
        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                System.out.printf("%4d", (mat_1[i][j] - mat_2[i][j]));
            }
            System.out.println();
        }
    }
}
