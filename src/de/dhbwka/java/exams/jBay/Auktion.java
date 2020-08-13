package de.dhbwka.java.exams.jBay;

import java.util.Calendar;

public class Auktion {
    private Ware item;
    private Gebot bid;
    private double price = 0.0;
    private Calendar end;
    private static double increment = 1.0;

    public Auktion(Ware item, int dauer) {
        this.item = item;

        this.end = Calendar.getInstance();
        this.end.setTimeInMillis(System.currentTimeMillis() + 60000 * dauer);
    }

    public Ware getItem() {
        return item;
    }

    public double getPrice() {
        return price;
    }

    public Gebot getBid() {
        return bid;
    }

    public Calendar getEnd() {
        return end;
    }

    public static double getIncrement() {
        return increment;
    }

    public boolean gebotAbgeben(Gebot g) {
        boolean result = true;

        if(this.price == 0.0) {
            this.price = increment;
            this.bid = g;
        }

        if(g.getMaxPrice() <= (this.price + increment)) {
            result = false;
        }

        if(g.getBidder() == this.bid.getBidder()) {
            this.bid = g;
        }

        if(g.getMaxPrice() >= (this.price + increment)) {
            if(g.getMaxPrice() <= this.bid.getMaxPrice()) {
                this.price = g.getMaxPrice() + increment;
            } else {
                this.price = this.bid.getMaxPrice() + increment;
                this.bid = g;
            }
        }

        return result;
    }
}