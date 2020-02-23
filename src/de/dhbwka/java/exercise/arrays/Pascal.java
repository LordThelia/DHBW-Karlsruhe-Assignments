package de.dhbwka.java.exercise.arrays;

public class Pascal {
    public static void main(String[] args) {
        final int MAX_ROWS = 9;
        int[][] triangle = new int[MAX_ROWS][];

        for (int i = 0; i < MAX_ROWS; ++i) {
            // build base
            triangle[i] = new int[i+1];
            triangle[i][0] = 1;
            triangle[i][triangle[i].length-1] = 1;

            // addition from rows/cols above
            if(i > 1) {
                for(int j = 1; j < triangle[i].length-1; ++j) {
                    triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j];
                }
            }
        }

        // print the triangle
        for (int i = 0; i < MAX_ROWS; ++i) {
            // print blanks to fill gaps
            for(int j = i; j < MAX_ROWS; ++j) {
                System.out.print(" ");
            }

            // print the row
            for(int j = 0; j < triangle[i].length; ++j) {
                System.out.printf("%3d", triangle[i][j]);
            }
            System.out.println();
        }
    }
}
