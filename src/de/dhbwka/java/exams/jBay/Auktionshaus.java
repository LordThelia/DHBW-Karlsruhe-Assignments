package de.dhbwka.java.exams.jBay;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Auktionshaus implements Runnable {
    private ArrayList<Auktion> auctions = new ArrayList<Auktion>();
    private ArrayList<BieterTerminal> terminals = new ArrayList<BieterTerminal>();
    private boolean isRunning = true;

    public void addAuktion(Auktion a) {
        this.auctions.add(a);
    }

    public void removeAuktion(Auktion a) {
        this.auctions.remove(a);
    }

    public List<Auktion> getAuktionen() {
        return this.auctions;
    }

    public void register(BieterTerminal bt) {
        this.terminals.add(bt);
    }

    public void unregister(BieterTerminal bt) {
        this.terminals.remove(bt);
    }

    public void updateTerminals() {
        this.terminals.forEach(BieterTerminal::update);
    }

    @Override
    public void run() {
        while(this.isRunning) {
            try {
                this.terminals.forEach(BieterTerminal::updateTime);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void log(Gebot g, Ware w) {
        File auctions = new File("auktionen.txt");

        if(auctions.exists()) {
            try(PrintWriter pw = new PrintWriter(new FileWriter(auctions, true))) {
                pw.println("[" + Calendar.getInstance().getTime().toString() + "] Gebot von " + g.getBidder().getFullName() + " für " + w.getTitle() + ": " + g.getMaxPrice() + " Euro.");
            } catch(IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
