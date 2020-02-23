package de.dhbwka.java.exercise.classes.vehicles;

public class Car extends Vehicle {
    public Car() {
        this(0.0);
    }

    public Car(double speed) {
        this(speed, 140.0);
    }

    protected Car(double speed, double maxSpeed) {
        super(4, speed, maxSpeed, 0.0);
    }
}
