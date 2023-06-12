package com.leetcode;

import java.util.Arrays;

public class BinarySearch  {


    public static void main(String ...args) {
       int[] arr = {4, 6, 7, 10, 20, 43, 67, 9, 5};
       int searchEl = 4;
       Arrays.sort(arr);
       int res = new BinarySearch().search(arr, 0, arr.length,  searchEl);
       System.out.println(res);
    }

    public int search(int[] arr, int searchEl) {
        return search(arr, 0, arr.length, searchEl);

    }


    public int search(int[] arr, int leftIndex, int rightIndex, int searchEl) {
        int middleIndex = (rightIndex + leftIndex)/ 2;
        if (rightIndex < leftIndex || arr.length <= middleIndex) {
            return -1;
        }
        int middleEl = arr[middleIndex];
        if(middleEl == searchEl) {
            return middleIndex;
        } else if (middleEl < searchEl) {
            return search(arr, middleIndex + 1, rightIndex, searchEl);
        } else {
            return search(arr, leftIndex, middleIndex - 1, searchEl);
        }
    }






}
