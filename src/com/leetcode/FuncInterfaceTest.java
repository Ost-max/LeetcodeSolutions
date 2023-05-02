package com.leetcode;

@FunctionalInterface
public interface FuncInterfaceTest {

    String test(String  a);

    default String test(int a) {
        return a + " test";
    }

    default String test(int a, int b) {
        return a + test(" test ");
    }

    static String test(String a, String b) {
        return a + b;
    }

}
