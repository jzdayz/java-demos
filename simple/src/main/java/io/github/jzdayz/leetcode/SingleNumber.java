package io.github.jzdayz.leetcode;

public class SingleNumber {

  public static void main(String[] args) {
    System.out.println(singleNumber(new int[]{2, 1, 2, 1, 9, 9, 3}));
  }

  public static int singleNumber(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
      nums[0] = nums[0] ^ nums[i];
    }
    return nums[0];
  }

}
