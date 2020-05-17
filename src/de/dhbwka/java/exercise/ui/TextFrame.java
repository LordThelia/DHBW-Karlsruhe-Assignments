package de.dhbwka.java.exercise.ui;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFrame extends JFrame {

    private int width;
    private int height;
    private String fileName;

    public TextFrame(int width, int height, String fileName) throws HeadlessException {
        this.width = width;
        this.height = height;
        this.fileName = fileName;

        List<String> linesToDisplay = readFile();

        JPanel panel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea();

        for (String line : linesToDisplay) {
            textArea.append(line);
            textArea.append(System.lineSeparator());
        }
        panel.add(textArea);

        add(panel);

        setTitle(fileName);
        setSize(width, height);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private List<String> readFile() {
        List<String> lines = new ArrayList<>();
        try (FileReader fileReader = new FileReader(new File(fileName)); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String thisLine;
            while ((thisLine = bufferedReader.readLine()) != null) {
                lines.add(thisLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println("Wrong argument size");
            return;
        }

        String fileName = args[0];
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IOException("The file does not exist");
        }

        int width = 400, height = 400;
        try {
            width = Integer.parseInt(args[1]);
            height = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.err.println("You didn't enter Numbers");
        }

        new TextFrame(width, height, fileName);
    }
}
