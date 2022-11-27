package com.leetcode;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Stack;

// https://leetcode.com/problems/basic-calculator/
public class BasicCalculator {

    public static void main(String[] args) throws ScriptException {
        String testExpr = "1 + 1 + (2 + 4 + (2 +3) +  (4 + 3)) +  1 + (2 +2)";
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Object evalResult = engine.eval(testExpr);
        int calcResult = new Solution().calculate(testExpr);
        System.out.println("evalResult: " + evalResult + " calcResult: " + calcResult);
        assert (Integer) evalResult == calcResult;
    }
}


class Solution {
    private final Stack<Operation> stack = new Stack<>();

    public int calculate(String s) {
        System.out.println(stack);
        int leftPart = 0;
        int count = 0;
        for (char ch : s.toCharArray()) {
            count++;
            if (Character.isDigit(ch)) {
                if (stack.empty()) {
                    leftPart = Character.getNumericValue(ch);
                } else {
                    int rightPart = Character.getNumericValue(ch);
                    leftPart = stack.pop().perform(rightPart);
                }
            }
            if (ch == '-') {
                stack.add(new Subtraction(leftPart));
            }
            if (ch == '+') {
                stack.add(new Addition(leftPart));
            }
            if (ch == '(') {
                return stack.pop().perform(calculate(s.substring(count)));
            }
            if (ch == ')') {
                return leftPart;
            }
        }
        return leftPart;
    }
}


interface Operation {
    int perform(int rightPart);
}


class Addition implements Operation {
    int leftPart;

    public Addition(int leftPart) {
        this.leftPart = leftPart;
    }

    @Override
    public int perform(int rightPart) {
        return leftPart + rightPart;
    }

    @Override
    public String toString() {
        return "Addition{" +
                "leftPart=" + leftPart +
                '}';
    }
}

class Subtraction implements Operation {
    int leftPart;

    public Subtraction(int leftPart) {
        this.leftPart = leftPart;
    }

    @Override
    public int perform(int rightPart) {
        return leftPart - rightPart;
    }

    @Override
    public String toString() {
        return "Subtraction{" +
                "leftPart=" + leftPart +
                '}';
    }
}