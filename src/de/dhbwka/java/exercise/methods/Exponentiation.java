package de.dhbwka.java.exercise.methods;

import java.util.Scanner;

public class Exponentiation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Base: ");
        double base = sc.nextDouble();

        System.out.print("Exponent: ");
        int exp = sc.nextInt();
        System.out.println();

        System.out.println(base + "^" + exp + " = " + xPowerN(base, exp));
    }

    public static double xPowerN(double x, int n) {
        if(n == 0) {
            return 1;
        }

        return x * xPowerN(x, n -1);
    }
}
