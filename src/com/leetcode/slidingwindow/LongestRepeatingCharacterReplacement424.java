package com.leetcode.slidingwindow;

import java.util.stream.Collectors;

//https://leetcode.com/problems/longest-repeating-character-replacement/?envType=list&envId=9oh5i7t2
public class LongestRepeatingCharacterReplacement424 {

    public static void main(String[] args) {
        System.out.println(new LongestRepeatingCharacterReplacement424().characterReplacement3("ABAB", 2)); // 4
        System.out.println(new LongestRepeatingCharacterReplacement424().characterReplacement3("AABABBA", 1)); // 4
        System.out.println(new LongestRepeatingCharacterReplacement424().characterReplacement3("ABBB", 2)); // 4
        System.out.println(new LongestRepeatingCharacterReplacement424().characterReplacement3("ABAA", 0)); // 2
        System.out.println(new LongestRepeatingCharacterReplacement424().characterReplacement3("ABCCDEEKKKOOKKOK", 2)); // 7
        System.out.println(new LongestRepeatingCharacterReplacement424().characterReplacement3("AAAA", 2)); // 4
        System.out.println(new LongestRepeatingCharacterReplacement424().characterReplacement3("AAAB", 0)); // 3
        System.out.println(new LongestRepeatingCharacterReplacement424().characterReplacement3("AABA", 0)); // 2
    }


    public int characterReplacement3(String s, int k) {
        // Make an array of size 26...
        int[] arr = new int[26];
        // Initialize largestCount, maxlen & beg pointer...
        int largestCount = 0, beg = 0, maxlen = 0;
        // Traverse all characters through the loop...
        for(int end = 0; end < s.length(); end ++){
            arr[s.charAt(end) - 'A']++;
            // Get the largest count of a single, unique character in the current window...
            largestCount = Math.max(largestCount, arr[s.charAt(end) - 'A']);
            // We are allowed to have at most k replacements in the window...
            // So, if max character frequency + distance between beg and end is greater than k...
            // this means we have considered changing more than k charactres. So time to shrink window...
            // Then there are more characters in the window than we can replace, and we need to shrink the window...
            if(end - beg + 1 - largestCount > k){     // The main equation is: end - beg + 1 - largestCount...
                arr[s.charAt(beg) - 'A']--;
                beg ++;
            }
            // Get the maximum length of repeating character...
            maxlen = Math.max(maxlen, end - beg + 1);     // end - beg + 1 = size of the current window...
        }
        return maxlen;      // Return the maximum length of repeating character...
    }
    public int characterReplacement2(String s, int k) {
        var maxLen = 1;
        for(var ch:  s.chars().mapToObj(e->(char)e).collect(Collectors.toSet()) ){
            int left = 0, right = 0, count = 0;
            while(right < s.length()) {
                if(s.charAt(right) != ch){
                    count++;
                }
                while(count> k) {
                    if(s.charAt(left) != ch) {
                        count--;
                    }
                    left++;
                }
                maxLen = Math.max(maxLen, (right - left + 1));
                right++;
            }
        }
        return maxLen;
    }



    public int characterReplacement(String s, int k) {
        var wind = new Wind();
        var interrupt = k;
        int i = 0;
        for(char ch: s.toCharArray()) {
            if(wind.symb == null) {
                wind.symb = ch;
            } else if(wind.symb == ch) {
                wind.length++;
            } else {
                if(interrupt > 0) {
                    wind.length++;
                }
                if(interrupt-- == 0) {
                    break;
                }
            }
            if(interrupt == k) {
                i++;
            }
        }

        var newWind = new Wind();
        var j = i;
        while (i<s.length()) {
            var ch = s.charAt(i);
            i++;
            if(newWind.symb == null) {
                interrupt = k;
                newWind.symb = ch;
            } else if(newWind.symb == ch) {
                newWind.length++;
            } else {
                if(interrupt-- == 0) {
                    if(newWind.length > wind.length) {
                        wind = newWind;
                    }
                    newWind = new Wind();
                    i = j;
                } else {
                    newWind.length++;
                }
            }
            if(interrupt == k) {
                j++;
            }
        }
        if(newWind.length > wind.length) {
            wind = newWind;
        }

     //   System.out.println(wind);

        return wind.length ;
    }


    class Wind2 {

        int left, right = 0;

        int k;


        @Override
        public String toString() {
            return "Wind{" +
                    "left=" + left +
                    ", right=" + right +
                    ", k=" + k +
                    '}';
        }

    }

    class Wind {

        Character symb;
        int length = 1;

        int nextCharIndex = -1;
        

        @Override
        public String toString() {
            return "Wind{" +
                    "symb=" + symb +
                    ", length=" + length +
                    '}';
        }
    }
}
