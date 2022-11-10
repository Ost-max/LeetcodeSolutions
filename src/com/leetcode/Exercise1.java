package com.leetcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exercise1 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("testcase1.txt"));
        String testCase = sc.nextLine();
        System.out.println("Result " + new Exercise1().numSub(testCase));
    }

        public int numSub(String s) {
            boolean newGroup = false;
            int module = 1000000007;
            int groupSize = 0;
            long totalCount = 0;
            for(char ch: s.toCharArray()) {
                if (Character.getNumericValue(ch) == 1) {
                    groupSize++;
                    newGroup = true;
                } else if (newGroup) {
                    newGroup = false;
                    long medRes =  (long)groupSize * (groupSize  + 1)/2 ;
                    totalCount = totalCount + medRes;
                    groupSize = 0;
                }
            }
            if (newGroup) {
                long medRes =  (long) groupSize * (groupSize + 1)/2;
                totalCount = totalCount + medRes;
            }
            System.out.println(totalCount);
            return (int) (totalCount % module);
        }

}
