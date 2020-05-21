package de.dhbwka.java.exercise.common.zoo;

public class ZooAnimal {
    String animal, name, fodder;

    public ZooAnimal(String animal, String name, String fodder) {
        this.animal = animal;
        this.name = name;
        this.fodder = fodder;
    }

    public String getName() {
        return name;
    }

    public String getAnimal() {
        return animal;
    }

    public void feed(String fodder) {
        System.out.println(this.name + " (" + this.animal + ") " + (fodder.equals(this.fodder) ? "eats " : "despises ") + fodder);
    }

    @Override
    public String toString() {
        return this.name + " (" + this.animal + ")";
    }
}
