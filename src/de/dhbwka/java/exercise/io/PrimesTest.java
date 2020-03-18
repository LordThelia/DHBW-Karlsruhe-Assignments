package de.dhbwka.java.exercise.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class PrimesTest {
    public static void main(String[] args) {
        PrimesFile primesFile = new PrimesFile(System.getProperty("user.home") + "\\Documents\\Java\\primes.txt", 100000);

        try {
            primesFile.createAndFillPrimesFile();
        } catch(IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        HashSet<String> hs = new HashSet<String>();

        try(BufferedReader br = new BufferedReader(new FileReader(primesFile.getFile()))) {
            while(br.ready()) {
                hs.add(br.readLine());
            }
        } catch(IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        int num = 0;
        do {
            System.out.print("Number: ");
            num = sc.nextInt();

            if(num > 0) {
                if(hs.contains(Integer.toString(num))) {
                    System.out.println(num + " is prime");
                } else {
                    System.out.println(num + " is not prime");
                }
            }
        } while(num > 0);
    }
}
