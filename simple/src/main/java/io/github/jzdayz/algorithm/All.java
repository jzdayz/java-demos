package io.github.jzdayz.algorithm;

public class All {

    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10));
    }

    private static int binarySearch(int[] data, int target) {
        int low = 0;
        int high = data.length - 1;
        while (low <= high) {
            int midIndex = (low + high) >> 1;
            int mid = data[midIndex];
            if (mid == target) {
                return midIndex;
            } else if (mid < target) {
                low = midIndex + 1;
            } else {
                high = midIndex - 1;
            }
        }
        return -1;
    }

}
