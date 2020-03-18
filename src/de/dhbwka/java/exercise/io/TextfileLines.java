package de.dhbwka.java.exercise.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextfileLines {
    public static void main(String[] args) {
        File textFile = new File("text.txt");
        StringBuilder sb = new StringBuilder();

        if(textFile.exists()) {
            try(BufferedReader br = new BufferedReader(new FileReader(textFile))) {
                int row = 0;
                while(br.ready()) {
                    ++row;
                    String line = br.readLine();

                    if(row >= 2 && row <= 5) {
                        sb.append(line);
                    }
                }

                System.out.println("Rows: " + sb.toString());
            } catch(IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
