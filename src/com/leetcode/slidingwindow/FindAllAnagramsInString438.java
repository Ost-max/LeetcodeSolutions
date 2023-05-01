package com.leetcode.slidingwindow;

import java.util.*;

//https://leetcode.com/problems/find-all-anagrams-in-a-string/
public class FindAllAnagramsInString438 {

    public static void main(String[] args) {
        var res = new FindAllAnagramsInString438().findAnagrams("cbaebabacd", "abc");
        System.out.println(res);
    }

    //Input: s = "cbaebabacd", p = "abc"
    //Output: [0,6]


    // bae
    // abc


    //Input: s = "abab", p = "ab"
    //Output: [0,1,2]

    public List<Integer> findAnagrams(String s, String p) {
        var result = new LinkedList<Integer>();
        var baseSet = new HashMap<Character, Integer>();
        var window = new HashMap<Character, Integer>();
        for(int i = 0; i<p.length(); i++) {
            baseSet.putIfAbsent(p.charAt(i), 1);
            baseSet.computeIfPresent(p.charAt(i), (key, val) -> val=val + 1);
        }
        for(int i = 0; i<s.length(); i++) {
            if(baseSet.containsKey(s.charAt(i))) {
                window.putIfAbsent(s.charAt(i), 1);
                window.computeIfPresent(s.charAt(i), (key, val) -> val=val + 1);
            }

            if(i >= p.length()) {
                var lookup = s.charAt(i - p.length());
                window.computeIfPresent(lookup, (key, val) -> --val);
                window.remove(lookup, 0);
            }
            if(window.size() == baseSet.size()) {
                result.add(i - p.length() + 1);
            }
        }
        return result;
    }

}
