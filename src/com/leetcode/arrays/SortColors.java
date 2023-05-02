package com.leetcode.arrays;

import java.util.Arrays;

public class SortColors {

    public static void main(String[] args) {
        int[] arr = {28, 4, 10, 3, 5, 1, 9, 0, 37};
        new HeapSort().sort(arr);
        System.out.println(Arrays.toString(arr));

    }

}
