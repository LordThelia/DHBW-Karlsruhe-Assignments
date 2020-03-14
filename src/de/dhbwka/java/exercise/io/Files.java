package de.dhbwka.java.exercise.io;

import java.io.File;

public class Files {
    public static void main(String[] args) {
        File path = new File(System.getProperty("user.home") + "\\Documents\\Java");
        path.mkdir();

        File[] files = new File[3];

        for(int i = 0; i < files.length; ++i) {
            files[i] = new File(path, "foo" + i);

            try {
                files[i].createNewFile();
            } catch (Exception e) {
                System.err.println("Error creating - Path: " + files[i].getAbsolutePath());
            }
        }

        System.out.println("Path: " + path.getAbsolutePath());
        for(File f: path.listFiles()) {
            System.out.println("File: " + f.getAbsolutePath());
        }

        for(File f: files) {
            f.delete();
        }
        path.delete();
    }
}
