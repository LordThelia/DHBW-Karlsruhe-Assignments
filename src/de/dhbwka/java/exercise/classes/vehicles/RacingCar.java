package de.dhbwka.java.exercise.classes.vehicles;

public class RacingCar extends Car {
    public RacingCar() {
        this(0.0);
    }

    public RacingCar(double speed) {
        this(speed, 220.0);
    }

    public RacingCar(double speed, double maxSpeed) {
        super(speed, maxSpeed);
    }
}
