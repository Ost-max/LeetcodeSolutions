package com.leetcode.twopointers;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

public class FindMaxSum {


    // (a + b)/120 = a/120 + b/120

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FindMaxSum().find(new int[]{6, 60, 140, 61, 100, 300, 59}, 120)));

        try {
            var arr = Files.readAllLines(Paths.get("28131_B.txt")).stream().mapToInt(Integer::parseInt).toArray();
         var res2 =  new FindMaxSum().find( Arrays.copyOfRange(arr, 1, arr.length), 120) ;
            System.out.println(Arrays.toString(res2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }




      /*
      140    + 100



            6
            60
            140
            61
            100
            300
            59
            */

    public int[] find2(int[] arr, int m) {
        var tempMap = new HashMap<Integer, Integer>();
        var res = new int[2];
        var max = 0;
        for(int i = 0; i<arr.length; i++) {
            int test = Math.abs(arr[i] - m);
            if(tempMap.containsKey(test)) {
               var test2 = arr[tempMap.get(test)];
               var sum = arr[i] + test2;
               if(sum > max && arr[i] < test2) {
                   max = sum;
                   res[0] = arr[i];
                   res[1] = test2;
               }
            }
            tempMap.put(test, i);
        }
        return res;
    }

    public int[] find(int[] arr, int m) {
        var max = 0;
        var res = new int[2];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                var tempSum = arr[i] + arr[j];
                if (tempSum % m == 0 & max < tempSum && arr[i] > arr[j]) {
                    res[0] = arr[i];
                    res[1] = arr[j];
                    max  = tempSum;
                }
            }
        }
        return res;
    }

}
