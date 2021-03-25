package io.github.jzdayz.leetcode;

import java.util.Arrays;

public class Merge {

  public static void main(String[] args) {
    int[] merge = merge(new int[]{1, 2, 3}, new int[]{1, 5, 6});
    System.out.println(Arrays.toString(merge));
  }

  private static int[] merge(int[] a, int[] b) {
    int[] rs = new int[a.length + b.length];
    int aI = a.length - 1;
    int bI = b.length - 1;
    int rsI = rs.length - 1;

    while ((aI >= 0) && (bI >= 0)) {
      rs[rsI--] = a[aI] > b[bI] ? a[aI--] : b[bI--];
    }
    return rs;
  }

}
