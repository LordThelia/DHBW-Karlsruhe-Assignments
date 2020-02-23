package de.dhbwka.java.exercise.methods;

public class Quicksort {
    public static void main(String[] args) {
        int[] arr = {9, 2, 5, 7, 8, 3, 4, 10, 1, 6};

        System.out.print("Raw: ");
        for(int i: arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        sort(arr);

        System.out.print("Sorted: ");
        for(int i: arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void sort(int[] array) {
        quicksort(array, 0, array.length - 1);
    }

    private static void quicksort(int[] arr, int left, int right) {
        if(left < right) {
            int divider = divide(arr, left, right);
            quicksort(arr, left, divider - 1);
            quicksort(arr, divider + 1, right);
        }
    }

    private static int divide(int[] arr, int left, int right) {
        int i = left;
        int j = right - 1;
        int pivot = arr[right];

        do {
            while((arr[i] <= pivot) && (i < right)) {
                ++i;
            }

            while((arr[j] >= pivot) && (j > left)) {
                --j;
            }

            if(i < j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        } while(i < j);

        // switch pivot with final position
        if(arr[i] > pivot) {
            int tmp = arr[i];
            arr[i] = arr[right];
            arr[right] = tmp;
        }

        return i;
    }
}
