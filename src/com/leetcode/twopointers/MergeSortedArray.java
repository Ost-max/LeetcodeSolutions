package com.leetcode.twopointers;

import java.util.Arrays;

public class MergeSortedArray {

    public static void main(String[] args) {
        new MergeSortedArray().merge(new int[]{3, 4, 7, 8, 10, 15, 0, 0, 0, 0}, new int[]{2, 5, 7, 16}, 6, 4);
        new MergeSortedArray().merge(new int[]{1, 2, 3, 0, 0, 0}, new int[]{2, 5, 6}, 3, 3);
        new MergeSortedArray().merge(new int[]{0}, new int[]{1}, 0, 1);
        new MergeSortedArray().merge(new int[]{0}, new int[]{}, 1, 0);
        new MergeSortedArray().merge(new int[]{1,2,3,0,0,0}, new int[]{4,5,6}, 3, 3);
        new MergeSortedArray().merge(new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                new int[]{-50,-50,-48,-47,-44,-44,-37,-35,-35,-32,-32,-31,-29,-29,-28,-26,-24,-23,-23,-21,-20,-19,-17,-15,-14,-12,-12,-11,-10,-9,-8,-5,-2,-2,1,1,3,4,4,7,7,7,9,10,11,12,14,16,17,18,21,21,24,31,33,34,35,36,41,41,46,48,48},
                0,
                63);
    }



    public void merge(int[] nums1, int[] nums2, int m, int n) {
        System.out.println(Arrays.toString(nums1) + " " + Arrays.toString(nums2));
        var p1 = m -1;
        var p2 = n - 1;
        if(n == 0) {
/*
            System.out.println(Arrays.toString(nums1));
*/
            return;
        }
        for(int i = nums1.length - 1; i >= 0; i--) {
            var num1 = nums1[Math.max(p1, 0)];
            var num2 = nums2[Math.max(p2, 0)];
            if(num2 > num1) {
                p2--;
                if (p2 < -1) {
                    break;
                }
                nums1[i] = num2;
            } else {
                p1--;
                nums1[i] = num1;
            }
            if(p1 < -1) {
                p2--;
                nums1[i] = Math.min(num1, num2);
            }
        }
        System.out.println(Arrays.toString(nums1));
    }

}
