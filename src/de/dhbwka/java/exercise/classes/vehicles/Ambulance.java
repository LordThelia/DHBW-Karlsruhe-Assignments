package de.dhbwka.java.exercise.classes.vehicles;

public class Ambulance extends Car {
    private boolean signal;

    public Ambulance() {
        this(0.0, false);
    }

    public Ambulance(double speed) {
        this(speed, false);
    }

    public Ambulance(double speed, boolean signal) {
        super(speed);
        this.signal = signal;
    }

    @Override
    public String toString() {
        return super.toString() + " Signal " + (signal ? "on" : "off");
    }

    public boolean hasSignal() {
        return signal;
    }

    public void setSignal(boolean signal) {
        this.signal = signal;
    }
}
