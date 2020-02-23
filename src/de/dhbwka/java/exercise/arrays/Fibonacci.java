package de.dhbwka.java.exercise.arrays;

public class Fibonacci {
    public static void main(String[] args) {
        long[] fibArr = new long[25];
        fibArr[0] = 1;
        fibArr[1] = 1;

        for (int i = 0; i < fibArr.length - 2; i++) {
            fibArr[i+2] = fibArr[i] + fibArr[i + 1];
        }

        for (int i = 0; i < fibArr.length; i++) {
            System.out.println(fibArr[i]);
        }
    }
}
