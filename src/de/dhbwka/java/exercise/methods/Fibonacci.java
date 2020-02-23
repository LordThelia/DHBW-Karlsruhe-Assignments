package de.dhbwka.java.exercise.methods;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int target;
        boolean correct;

        do {
            correct = true;
            System.out.print("Up to: ");
            target = sc.nextInt();

            if(target < 1) {
                correct = false;
                System.out.println("to small");
            }
        } while(!correct);

        sc.close();

        for(int i = 1; i <= target; ++i) {
            System.out.printf("F(%d) = %3d\n", i, fibonacci(i));
        }
    }

    public static long fibonacci(long n) {
        if(n <= 2) {
            return 1;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
