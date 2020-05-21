package de.dhbwka.java.exercise.common.zoo;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Zoo {
    private final int MAX_ANIMALS;
    private List<ZooAnimal> animals;

    public Zoo() {
        this(5);
    }

    public Zoo(int max) {
        this.MAX_ANIMALS = max;
        this.animals = new ArrayList<ZooAnimal>();
    }

    public void addAnimal(ZooAnimal animal) throws ZooCapacityException {
        if(this.animals.size() < this.MAX_ANIMALS) {
            this.animals.add(animal);
            System.out.println(animal.toString() + " added to zoo");
        } else {
            throw new ZooCapacityException("Error: zoo capacity exceeded!");
        }
    }

    public ZooAnimal[] getAnimals() {
        ZooAnimal[] zooAnimals = new ZooAnimal[this.MAX_ANIMALS];

        for(int i = 0; i < this.animals.size(); ++i) {
            zooAnimals[i] = this.animals.get(i);
        }

        return zooAnimals;
    }

    public boolean existsAnimal(String name) {
        boolean result = false;

        for (ZooAnimal animal : this.animals) {
            if(name.equals(animal.getName())) {
                result = true;
            }
        }

        return result;
    }

    public void feed(String fodder) {
        for (ZooAnimal animal : this.animals) {
            animal.feed(fodder);
        }
    }

    public void saveToFile(String filename) throws ZooFileException {
        File zooAnimals = new File(filename);

        try(PrintWriter pw = new PrintWriter(new FileWriter(zooAnimals))) {
            for (ZooAnimal animal : this.animals) {
                pw.println(animal.getAnimal() + ";" + animal.getName() + ";" + animal.getClass().getSimpleName());
            }

            System.out.println("Saved animals to file!");
        } catch(IOException e) {
            throw new ZooFileException();
        }
    }

    public static void main(String[] args) {
        Zoo z = new Zoo();

        String[][] newPredators = new String[][] {{"Tiger", "Fred"}, {"Tiger", "Lisa"}, {"Lion", "Simba"}};
        String[][] newBirds = new String[][] {{"Nuthatch", "Hansi"}, {"Backbird", "Sina"}, {"Wren", "Henry"}};

        for (String[] animal : newPredators) {
            try {
                z.addAnimal(new Predator(animal[0], animal[1]));
            } catch (ZooCapacityException e) {
                System.err.println(e.getMessage());
            }
        }

        for (String[] animal : newBirds) {
            try {
                z.addAnimal(new Songbird(animal[0], animal[1]));
            } catch (ZooCapacityException e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println("");

        try {
            z.saveToFile("ZooAnimals.txt");
        } catch (ZooFileException e) {
            System.err.println("Could not save File: " +  e.getMessage());
        }
        System.out.println("");

        z.feed("grains");
        System.out.println("");

        System.out.println("Is 'Fred' in the zoo? " + (z.existsAnimal("Fred") ? "yes" : "no"));
        System.out.println("Is 'Kimba' in the zoo? " + (z.existsAnimal("Kimba") ? "yes" : "no"));
        System.out.println("Is 'Henry' in the zoo? " + (z.existsAnimal("Henry") ? "yes" : "no"));
        System.out.println("Is 'Lotte' in the zoo? " + (z.existsAnimal("Lotte") ? "yes" : "no"));
    }
}
