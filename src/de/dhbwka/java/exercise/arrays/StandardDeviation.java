package de.dhbwka.java.exercise.arrays;

public class StandardDeviation {
    public static void main(String[] args) {
        int size = 100;
        double[] array = new double[size];
        double sum = 0;

        for (int i = 0; i < size; ++i) {
            array[i] = Math.random() * 100;
            sum += array[i];
        }

        System.out.println("Average: " + ((1.0 / (double) size) * sum));

        for (int i = 0; i < size; i++) {
            sum += Math.pow(array[i], 2);
        }

        double standardDeviation = Math.sqrt(sum / (array.length - 1));
        System.out.println("Standard deviation: " + standardDeviation);
    }
}
