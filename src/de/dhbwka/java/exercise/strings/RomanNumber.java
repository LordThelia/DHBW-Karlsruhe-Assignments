package de.dhbwka.java.exercise.strings;

import java.util.Scanner;

public class RomanNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Number: ");
        String number = sc.nextLine();

        System.out.println("Value: " + getValue(number));
        sc.close();
    }

    private static int getValue(String num) {
        int result = 0;

        for(int i = 0; i < num.length() - 1; ++i) {
            int val = getDecimalValue(num.charAt(i));

            if(val < getDecimalValue(num.charAt(i + 1))) {
                result -= val;
            } else {
                result += val;
            }
        }
        // Add last digit
        result += getDecimalValue(num.charAt(num.length() - 1));

        return result;
    }

    private static int getDecimalValue(char c) {
        switch (Character.toUpperCase(c)) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}
