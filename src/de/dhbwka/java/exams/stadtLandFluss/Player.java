package de.dhbwka.java.exams.stadtLandFluss;

public class Player {
    private String name;
    private int points = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
