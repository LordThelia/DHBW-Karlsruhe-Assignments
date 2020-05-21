package de.dhbwka.java.exercise.ui;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class TextfileViewer {
    private final int MAX_ROWS = 10;

    private JFrame frame;
    private JPanel panel;
    private JFileChooser fileChooser;
    private String filePath;

    public TextfileViewer() {
        this.frame = new JFrame();
        this.panel = new JPanel();

        this.frame.add(this.panel);
        this.panel.setLayout(new GridLayout(0,1));

        this.frame.setSize(300, 300);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initFileChooser();
        chooseFile();

        this.frame.setVisible(true);
    }

    private void initFileChooser() {
        this.fileChooser = new JFileChooser();
        this.fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory() || file.getName().toLowerCase().endsWith(".txt");
            }

            @Override
            public String getDescription() {
                return "Text Files";
            }
        });
    }

    private void chooseFile() {
        int status = this.fileChooser.showOpenDialog(this.panel);

        if(status == JFileChooser.APPROVE_OPTION) {
            this.filePath = this.fileChooser.getSelectedFile().getAbsolutePath();

            if(!filePath.isEmpty()) {
                //addLabelsToPane(readFile());
                addLinesToTextArea(readFile());
            } else {
                JOptionPane.showMessageDialog(null, "No selection given!", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No selection given!", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    private void addLinesToTextArea(List<String> lines) {
        JTextArea textArea = new JTextArea();

        if(lines.size() > MAX_ROWS) {
            JScrollPane scrollPane = new JScrollPane(textArea);
            panel.add(scrollPane);
        } else {
            panel.add(textArea);
        }

        for(String line : lines) {
            textArea.append(line);
            textArea.append(System.lineSeparator());
        }
    }

    private void addLabelsToPane(List<String> lines) {
        int counter = 0;

        for(String line : lines) {
            if(counter < MAX_ROWS) {
                JLabel label = new JLabel(line);
                this.panel.add(label);

                ++counter;
            }
        }
    }

    private List<String> readFile() {
        List<String> lines = new ArrayList<String>();

        try (FileReader fileReader = new FileReader(new File(this.filePath)); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String thisLine;
            while ((thisLine = bufferedReader.readLine()) != null) {
                lines.add(thisLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void main(String[] args) {
        new TextfileViewer();
    }
}
