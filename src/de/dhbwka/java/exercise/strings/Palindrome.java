package de.dhbwka.java.exercise.strings;

import java.io.*;
import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Word: ");
        String word = sc.nextLine();
        StringBuilder sb = new StringBuilder(word);
        StringBuilder rev = new StringBuilder(word).reverse();

        System.out.println("Reversed: " + rev);
        System.out.println("Palindrome: " + word.equalsIgnoreCase(rev.toString()));

        sc.close();
    }
}
