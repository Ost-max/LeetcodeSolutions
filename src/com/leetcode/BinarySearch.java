package com.leetcode;

import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;

public class BinarySearch implements FuncInterfaceTest, FuncInterfaceTestTwo {


    public static void main(String ...args) {
       int[] arr = {4, 6, 7, 10, 20, 43, 67, 9, 5};
       int searchEl = 4;
       Arrays.sort(arr);
       int res = binarySearch(arr, 0, arr.length,  searchEl);
       System.out.println(res);
       System.out.println(Arrays.toString(arr));
       System.out.println(arr.length);
       System.out.println(arr[res]);

    }


    private static int binarySearch(int[] arr,int leftIndex, int rightIndex,   int searchEl) {
        int middleIndex = (rightIndex + leftIndex)/ 2;
        if(rightIndex < leftIndex || arr.length <= middleIndex) {
            return -1;
        }
        int middleEl = arr[middleIndex];
        if(middleEl == searchEl) {
            return middleIndex;
        } else if (middleEl < searchEl) {
            return binarySearch(arr, middleIndex + 1, rightIndex, searchEl);
        } else {
            return binarySearch(arr, leftIndex, middleIndex - 1, searchEl);
        }
    }



    @Override
    public String test(String a) {
        return null;
    }

    @Override
    public String test(int a) {
        return FuncInterfaceTest.super.test(a);
    }



}
