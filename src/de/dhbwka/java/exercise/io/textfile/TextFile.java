package de.dhbwka.java.exercise.io.textfile;

import javax.sound.sampled.Line;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextFile {
    private File textFile;
    List<String> rows = new ArrayList<String>();

    public TextFile(File f) {
        this.textFile = f;
    }

    public TextFile(String pathname) {
        this(new File(pathname));
    }

    public void read() {
        this.rows.clear();

        try(BufferedReader br = new BufferedReader(new FileReader(this.textFile))) {
            while(br.ready()) {
                this.rows.add(br.readLine());
            }
        } catch(FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void write() {
        try (FileWriter fw = new FileWriter(this.textFile)) {
            for (String line : this.rows) {
                fw.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public int availableLines() {
        return this.rows.size();
    }

    public String[] getLines() {
        String[] lines = new String[this.rows.size()];

        for(int i = 0; i < this.rows.size(); ++i) {
            lines[i] = this.rows.get(i);
        }

        return lines;
    }

    public String getLine(int i) throws LineNumberOutOfBoundsException {
        if(i < 1 && i > this.rows.size()) {
            throw new LineNumberOutOfBoundsException("Invalid line number");
        }

        return this.rows.get(i - 1);
    }

    public void setLine(int i, String s) throws LineNumberOutOfBoundsException {
        if(i < 1 && i > this.rows.size()) {
            throw new LineNumberOutOfBoundsException("Invalid line number");
        }

        this.rows.set(i - 1, s);
    }

    public void replaceAll(String regexp, String ersatz) {
        for (int i = 0; i < this.rows.size(); ++i) {
            this.rows.set(i, this.rows.get(i).replaceAll(regexp, ersatz));
        }
    }

    public void close() {
        write();
        this.rows.clear();
    }
}
