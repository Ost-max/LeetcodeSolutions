package com.leetcode.arrays;

import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/
public class FindAllNumbersDisappearedInAnArray448 {

    public static void main(String[] args) {
        System.out.println( new FindAllNumbersDisappearedInAnArray448().findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1}));
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        var res = new LinkedList<Integer>();

        for(int i = 0; i<nums.length; i++) {
                 nums[Math.abs(nums[i]) - 1] = nums[Math.abs(nums[i]) -1]  > 0 ? nums[Math.abs(nums[i]) -1] * (-1) : nums[Math.abs(nums[i]) - 1];
        }

        for(int i = 0; i<nums.length; i++) {
            if(nums[i] > 0) {
                res.add(i + 1);
            }
        }

        return res;
    }
}
