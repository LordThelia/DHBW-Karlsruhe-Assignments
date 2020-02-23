package de.dhbwka.java.exercise.strings;

import java.util.Scanner;

public class CrossTotal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Number for cross total: ");
        String num = sc.next();
        int total = 0;

        for(char c : num.toCharArray()) {
            total += (c - '0');
        }

        System.out.println("Cross total: " + total);

        sc.close();
    }
}
