package io.github.jzdayz.leetcode;

public class A {

  public static void main(String[] args) {
    int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int i = binarySearch0(ints, 0, ints.length, 3);
    System.out.println(i);
  }

  private static int binarySearch0(int[] a, int fromIndex, int toIndex,
      int key) {
    int low = fromIndex;
    int high = toIndex - 1;

    while (low <= high) {
      int mid = (low + high) >>> 1;
      int midVal = a[mid];

      System.out.printf("low:%s high:%s mid:%s midVal:%s%n", low, high, mid, midVal);

      if (midVal < key) {
        low = mid + 1;
      } else if (midVal > key) {
        high = mid - 1;
      } else {
        return mid; // key found
      }
    }
    return -(low + 1);  // key not found.
  }

}