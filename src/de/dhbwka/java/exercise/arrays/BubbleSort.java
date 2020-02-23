package de.dhbwka.java.exercise.arrays;

import java.util.Scanner;

public class BubbleSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many elements? ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < arr.length; i++) {
            System.out.print("x" + i + ": ");
            arr[i] = sc.nextInt();
        }

        for(int i = 1; i < arr.length; ++i) {
            for(int j = 0; j < arr.length - i; ++j) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        System.out.print("Sorted: ");
        for (int i : arr) {
            System.out.print(i + " ");
        }

        sc.close();
    }
}
