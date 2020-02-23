package de.dhbwka.java.exercise.classes.vehicles;

public class Bicycle extends Vehicle {
    public Bicycle() {
        this(0);
    }

    public Bicycle(double speed) {
        super(2, speed, 30, 0);
    }
}
