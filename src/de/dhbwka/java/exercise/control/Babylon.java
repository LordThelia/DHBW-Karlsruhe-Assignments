package de.dhbwka.java.exercise.control;

import java.util.Scanner;

public class Babylon {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Root of which number? ");
        double a = scan.nextInt();
        scan.close();

        double xn = 1.0;
        double temp;

        do {
            temp = xn;
            xn = (xn + (a / xn)) / 2;
        } while (Math.abs(temp - xn)>=10E-6);

        System.out.println("The root of " + a + " is " + xn);
    }
}
