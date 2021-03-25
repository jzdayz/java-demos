package io.github.jzdayz.leetcode;

import java.lang.reflect.AnnotatedWildcardType;
import java.util.HashSet;
import java.util.Set;

public class SearchMatrix {

  public static void main(String[] args) {
    int[][] matrix = {
        new int[]{1, 4, 7, 11, 15},
        new int[]{2, 5, 8, 12, 19},
        new int[]{3, 6, 9, 16, 22},
        new int[]{10, 13, 14, 17, 24}
    };

    valid(matrix);
    System.out.println("OK~~");
  }

  private static void valid(int[][] matrix) {

    Set<Integer> va = new HashSet<>();
    for (int[] ints : matrix) {
      for (int anInt : ints) {
        va.add(anInt);
      }
    }

    for (int i = 1; i < 100; i++) {
      boolean searchResult = search(matrix, i);

      if (va.contains(i) && !searchResult){
        throw new RuntimeException();
      }
    }
  }

  private static boolean search(int[][] matrix, int target) {
    int[] row1 = matrix[0];
    if (target < row1[0]) {
      return false;
    }
    // 横着最大的特殊情况
    if (target > row1[row1.length - 1]) {
      if (search2(matrix, row1.length - 1, target) == -1) {
        return true;
      }
    }
    // 先横着来
    int rs = search1(matrix, target, 0);
    if (rs == -1){
      return true;
    }
    // 然后竖着来
    if (search2(matrix, rs, target) == -1){
      return true;
    }




    // 竖着的最大的特殊情况
    if (target > matrix[matrix.length - 1][0]){
      if (search1(matrix,target,matrix.length - 1) == -1){
        return true;
      }
    }
    // 竖着来
    rs = search2(matrix, 0, target);
    if (rs == -1){
      return true;
    }
    // 横着来
    if (search1(matrix,target,rs) == -1){
      return true;
    }
    // 最后就是没找到
    return false;
  }

  public static int search1(int[][] matrix, int target, int rowIndex) {
    int[] row1 = matrix[rowIndex];

    for (int i = row1.length / 2,
        minIndex = 0,
        maxIndex = row1.length - 1; ; i = (maxIndex - minIndex) / 2 + minIndex) {

      if (target > row1[i]) {
        minIndex = i;
      } else if (target < row1[i]) {
        maxIndex = i;
      } else {
        return -1;
      }

      if (maxIndex - minIndex == 1) {
        return minIndex;
      }

    }
  }

  private static int search2(int[][] matrix, int everyIndex, int target) {

    for (int i = matrix.length / 2, minIndex = 0, maxIndex =
        matrix.length - 1; ;
        i = (maxIndex - minIndex) / 2 + minIndex) {
      int[] anInt = matrix[i];
      if (anInt[everyIndex] > target) {
        maxIndex = i;
      } else if (anInt[everyIndex] < target) {
        minIndex = i;
      } else {
        return -1;
      }

      if (maxIndex - minIndex == 1) {
        return minIndex;
      }
    }
  }


}
