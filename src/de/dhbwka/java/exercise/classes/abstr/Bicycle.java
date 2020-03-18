package de.dhbwka.java.exercise.classes.abstr;

public class Bicycle extends Vehicle {
    public Bicycle() {
        this(0);
    }

    public void info() {
        System.out.println(this.toString());
    }

    public Bicycle(double speed) {
        super(2, speed, 30, 0);
    }
}
