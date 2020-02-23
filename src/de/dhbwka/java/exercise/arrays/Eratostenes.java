package de.dhbwka.java.exercise.arrays;

public class Eratostenes {
    public static void main(String[] args) {
        int count = 100;
        boolean[] prim = new boolean[count];

        for (int i = 2; i < prim.length; i++) {
            prim[i] = true;
        }

        for (int i = 2; i < prim.length; i++) {
            if (prim[i]) {
                for (int j = i * 2; j < prim.length; j += i) {
                    prim[j] = false;
                }
            }
        }

        for (int i = 0; i < prim.length; i++) {
            if (prim[i]) {
                System.out.println(i);
            }
        }
    }
}
