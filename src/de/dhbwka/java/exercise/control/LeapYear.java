package de.dhbwka.java.exercise.control;

import java.util.Scanner;

public class LeapYear {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Which year? ");
        int year = scan.nextInt();
        scan.close();

        boolean isLeap = false;
        if(year % 4 == 0) {
            if(year % 100 == 0) {
                if(year % 400 == 0) {
                    isLeap = true;
                }
            } else {
                isLeap = true;
            }
        }

        System.out.println(year + " is " + (isLeap ? "a" : "no") + " leapyear");
    }
}
