package de.dhbwka.java.exercise.operators;

import java.util.Scanner;

public class Easter {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("For which year? ");
        int year = scan.nextInt();
        scan.close();

        int a = year % 19;
        int b = year % 4;
        int c = year % 7;
        int k = year / 100;
        int p = (8 * k + 13) / 25;
        int q = k / 4;
        int M = (15 + k - p - q) % 30;
        int N = (4 + k - q) % 7;
        int d = (19 * a + M) % 30;
        int e = (2 * b + 4 * c + 6 * d + N) % 7;
        int easter = (22 + d + e);

        System.out.println("In " + year + " easter is on " + (easter < 32 ? easter : easter - 31) + ". " + (easter < 32 ? "March" : "April"));
    }
}
