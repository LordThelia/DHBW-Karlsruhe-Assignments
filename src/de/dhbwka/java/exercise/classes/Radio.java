package de.dhbwka.java.exercise.classes;

public class Radio {
    private boolean on;
    private int volume;
    private double frequency;

    public Radio() {
        this.on = false;
        this.volume = 5;
        this.frequency = 100.0;
    }

    public Radio(boolean on, int volume, double frequency)  {
        this.on = on;
        this.volume = volume;
        this.frequency = frequency;
    }

    public void incVolume() {
        if(this.on && this.volume < 10) {
            ++this.volume;
        }
    }

    public void decVolume() {
        if(this.on && this.volume > 0) {
            --this.volume;
        }
    }

    public void turnOn() {
        this.on = true;
    }

    public void turnOff() {
        this.on = false;
    }

    public void setFrequency(double frequency) {
        if(frequency >= 85.0 && frequency <= 110.0) {
            this.frequency = frequency;
        } else {
            this.frequency = 99.0;
        }
    }

    public String toString() {
        return "Radio " + (this.on ? "an" : "aus") + " Freq=" + this.frequency + ", Laut=" + this.volume;
    }
}