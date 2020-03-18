package de.dhbwka.java.exercise.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrimesFile {
    private File primeFile;
    int length;

    public PrimesFile(File file, int length) {
        this.primeFile = file;
        this.length = length;
    }

    public PrimesFile(String path, int length) {
        this(new File(path), length);
    }

    public void createAndFillPrimesFile() throws IOException {
        boolean[] prime = new boolean[this.length];

        for (int i = 2; i < prime.length; i++) {
            prime[i] = true;
        }

        for (int i = 2; i < prime.length; i++) {
            if (prime[i]) {
                for (int j = i * 2; j < prime.length; j += i) {
                    prime[j] = false;
                }
            }
        }

        PrintWriter pw = new PrintWriter(new FileWriter(this.primeFile));

        for (int i = 0; i < prime.length; i++) {
            if (prime[i]) {
                pw.println(i);
            }
        }
    }

    public File getFile() {
        return this.primeFile;
    }
}
