package de.dhbwka.java.exercise.classes;

public class Lotto {
    int amountToDraw;
    int amountBalls;
    int[] tip;
    int[] drawnNumbers;
    java.util.Scanner sc;

    public Lotto(int toDraw, int allBalls) {
        amountToDraw = toDraw;
        amountBalls = allBalls;
        tip = new int[amountToDraw];
        drawnNumbers = new int[amountToDraw];
        sc = new java.util.Scanner(System.in);
    }

    public void play() {
        makeATip();
        sort(tip);
        drawBalls();
        sort(drawnNumbers);

        System.out.println(this.toString());
        System.out.println("Correct: " + getCorrect());
    }

    private void makeATip() {
        boolean hasError;

        for (int i = 0; i < amountToDraw; ++i) {
            do {
                System.out.print("Tip for number " + (i + 1) + ": ");
                hasError = false;
                tip[i] = sc.nextInt();

                for (int j = 0; j < i; ++j) {
                    if (tip[j] == tip[i]) {
                        System.out.println("Number already picked!");
                        hasError = true;
                    }
                }
            } while (hasError);
        }
    }

    private void drawBalls() {
        boolean isDrawn;

        for (int i = 0; i < amountToDraw; i++) {
            do {
                isDrawn = false;
                drawnNumbers[i] = (int) (Math.random() * 49 + 1);

                for (int k = 0; k < i; k++) {
                    isDrawn = drawnNumbers[k] == drawnNumbers[i];
                }
            } while (isDrawn);
        }
    }

    private int getCorrect() {
        int rightGuesses = 0;

        for (int i = 0; i < amountBalls - 1; ++i) {
            for (int k = 0; k < amountBalls - 1; ++k) {
                if (tip[i] == drawnNumbers[k]) {
                    ++rightGuesses;
                }
            }
        }
        return rightGuesses;
    }

    private void sort(int[] arr) {
        for(int i = 1; i < arr.length; ++i) {
            for(int j = 0; j < arr.length - i; ++j) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public String toString() {
        String str = "";
        if (tip[0] != 0) {
            str += "Tip: ";
            for (int i = 0; i < amountToDraw; ++i) {
                str += tip[i] + " ";
            }
            str += "\n";
        }

        if (drawnNumbers[0] != 0) {
            str += "Drawn numbers: ";
            for (int i = 0; i < amountToDraw; ++i) {
                str += drawnNumbers[i] + " ";
            }
            str += "\n";
        }

        return str;
    }
}
