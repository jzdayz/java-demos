package io.github.jzdayz.leetcode;

public class MajorityElement {

  public static void main(String[] args) {
    System.out.println(majorityElement(new int[]{5,23,5,1,5}));
  }

  public static int majorityElement(int[] nums) {
    int count = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == nums[0]) {
        count++;
      } else {
        count--;
        if (count == 0) {
          nums[0] = nums[i];
          count = 1;
        }
      }
    }
    return nums[0];
  }
}
