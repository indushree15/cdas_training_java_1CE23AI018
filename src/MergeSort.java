import java.util.Arrays;

public class MergeSort {

    // Main function that sorts the array using merge sort
    public static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;  // base case: an array of length 1 is already sorted
        }

        // Find the middle point
        int mid = arr.length / 2;

        // Split the array into two halves
        int[] left = Arrays.copyOfRange(arr, 0, mid);  // Left half
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);  // Right half

        // Recursively sort both halves
        mergeSort(left);
        mergeSort(right);

        // Merge the sorted halves back into the original array
        merge(arr, left, right);
    }

    // Merge two sorted arrays into the original array
    public static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // Merge elements from left and right arrays into arr[]
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        // If there are remaining elements in left array, copy them
        while (i < left.length) {
            arr[k] = left[i];
            i++;
            k++;
        }

        // If there are remaining elements in right array, copy them
        while (j < right.length) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    // Helper function to print the array
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // Main function to test the mergeSort
    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};

        System.out.println("Original array:");
        printArray(arr);

        mergeSort(arr);

        System.out.println("Sorted array:");
        printArray(arr);
    }
}
