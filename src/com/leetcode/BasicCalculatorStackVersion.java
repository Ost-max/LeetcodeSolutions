package com.leetcode;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/basic-calculator/
public class BasicCalculatorStackVersion {

    public static void main(String[] args) throws ScriptException {
        List<String> testCases = new ArrayList<>();
        testCases.add("1+5-4");
        testCases.add(" -(1+(4+51 +  2 )-3)+(6+8)");
        testCases.add("-2+ 1");
        testCases.add(" 1 + 1");

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        for(String testCase: testCases) {
            int evalResult = (Integer) engine.eval(testCase);
            int calcResult = new Calculator().calculate(testCase);
            if(evalResult != calcResult) {
                System.out.print("ERROR!!! ");
            }
            System.out.println("case: " + testCase + " evalResult: " + evalResult + " calcResult: " + calcResult);

        }
    }

    static class Calculator {
        private Stack<Integer> stack = new Stack<>();
        int result = 0;
        public int calculate(String s) {
            boolean minus = false;
            boolean add = false;

            for(char ch: s.toCharArray()) {
                if (ch == '+') {
                    add = true;
                } else if(ch == '-') {
                    minus = true;
                } else if (Character.isDigit(ch)) {
                    int i = Character.getNumericValue(ch);
                    i = minus ? i*(-1) : i;
                    stack.add(i);
                } else if(ch == '(') {

                } else if (ch == ')') {
                    while(stack.size() > 0) {
                        result = result + stack.pop();
                    }

                }
            }
            return result;
        }

        private boolean isOperation(char ch) {
            return "+-".indexOf(ch) > -1;
        }

    }
}





