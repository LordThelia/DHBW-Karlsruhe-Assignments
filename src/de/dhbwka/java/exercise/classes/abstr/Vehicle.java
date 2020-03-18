package de.dhbwka.java.exercise.classes.abstr;

public abstract class Vehicle {
    private int wheels;
    private double speed;
    private double maxSpeed;
    private double position;

    public Vehicle() {
        this(0);
    }

    public Vehicle(double speed) {
        this(0, speed, 0, 0);
    }

    protected Vehicle(int wheels, double speed, double maxSpeed, double position) {
        this.wheels = wheels;
        this.maxSpeed = maxSpeed;
        setSpeed(speed);
        this.position = position;
    }

    public abstract void info();

    public void drive(double minutes) {
        this.position += speed / 60.0 * minutes;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " at " + position + " km with " + wheels + " wheels at speed " + speed
                + " km/h of max. " + maxSpeed + " km/h";
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = Math.min(this.maxSpeed, speed);
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }
}
