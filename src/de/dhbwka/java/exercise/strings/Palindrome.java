package de.dhbwka.java.exercise.strings;

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Word: ");
        String word = sc.nextLine();
        String reversed = "";

        for(int i = word.length() - 1; i >= 0; --i) {
            reversed += word.charAt(i);
        }

        System.out.println("Reversed: " + reversed);
        System.out.println("Palindrome: " + word.equalsIgnoreCase(reversed));

        sc.close();
    }
}
