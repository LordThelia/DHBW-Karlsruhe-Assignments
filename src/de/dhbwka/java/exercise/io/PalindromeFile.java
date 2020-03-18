package de.dhbwka.java.exercise.io;

import java.io.*;
import java.util.Scanner;

public class PalindromeFile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Word: ");
        String word = sc.nextLine();
        StringBuilder sb = new StringBuilder(word);
        StringBuilder rev = new StringBuilder(word).reverse();
        sc.close();

        boolean isPalindrome = word.equalsIgnoreCase(rev.toString());
        System.out.println(word + " is " + (isPalindrome ? "a " : "not a ") + "palindrome");

        File palindromeFile = new File("palindromes.txt");

        if(isPalindrome) {
            try(PrintWriter pw = new PrintWriter(new FileWriter(palindromeFile, true))) {
                pw.println(word);
            } catch(IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        if(palindromeFile.exists()) {
            try(BufferedReader br = new BufferedReader(new FileReader(palindromeFile))) {
                while(br.ready()) {
                    System.out.println(br.readLine());
                }
            } catch(IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
