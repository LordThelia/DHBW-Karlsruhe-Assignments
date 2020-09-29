package de.dhbwka.java.exams.snatChat;

import java.awt.Color;
import java.util.Random;

public class Account {
    private String name;
    private State state = State.AVAILABLE;
    private Color color;

    public Account(String name) {
        this.name = name;

        Random rnd = new Random();
        this.color = new Color(rnd.nextInt(201), rnd.nextInt(201), rnd.nextInt(201));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
