package com.leetcode;

@FunctionalInterface
public interface FuncInterfaceTestTwo {

    String test(String  a);

    default String test(int a) {
        return a + " test";
    }

    default String bla(int a, int b) {
        return a + " test " + b;
    }

    static String test(String a, String b) {
        return a + b;
    }
}
