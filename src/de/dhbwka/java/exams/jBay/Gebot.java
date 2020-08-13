package de.dhbwka.java.exams.jBay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Gebot {
    private double maxPrice;
    private Bieter bidder;

    public Gebot(double maxPrice, Bieter bidder) {
        this.maxPrice = maxPrice;
        this.bidder = bidder;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public Bieter getBidder() {
        return bidder;
    }
}
