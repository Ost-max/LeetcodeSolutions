package com.leetcode.twopointers;

import java.util.HashMap;
import java.util.Map;

public class FindTargetSum {
     //  Массив интов на входе и число (например 100) - нужно найти и вывести индексы двух чисел, которые в сумме
    //  дают это число или написать, что таких чисел нет
   // [1,3, 75, 47,25, 99] 100 => 2,4


    public static void main(String[] args) {
        new FindTargetSum().printSum(new int[]{50, 1, 3, 75, 47, 25, 99}, 100);
    }

    private void  printSum(int[] arr, int target) {
        // [1,3, 75, 47,25, 99]
        // 0, 1, 2, ,3, 4,
        Map<Integer, Integer> tempMap = new HashMap<>();
        if(arr.length < 2) {
            return;
        }

        for(int i = 0; i<arr.length; i++) {
            int test = target - arr[i];
            if(tempMap.containsKey(test)) {
                System.out.println(i + " " + tempMap.get(test));
                return;
            }
            tempMap.put(arr[i], i);
        }
    }

}
