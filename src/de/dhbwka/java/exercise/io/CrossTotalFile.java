package de.dhbwka.java.exercise.io;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class CrossTotalFile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Number for cross total: ");
        String num = sc.next();
        sc.close();

        int total = 0;

        for(char c : num.toCharArray()) {
            total += (c - '0');
        }

        File path = new File(System.getProperty("user.home") + "\\Documents\\Java");
        path.mkdir();

        try (PrintWriter pw = new PrintWriter(new FileWriter(path + "\\Crosstotals.txt",true))) {
            pw.println("Crosstotal of " + num + " is: " + total);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
