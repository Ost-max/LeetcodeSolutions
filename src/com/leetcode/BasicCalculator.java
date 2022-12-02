package com.leetcode;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Stack;

// https://leetcode.com/problems/basic-calculator/
public class BasicCalculator {

    public static void main(String[] args) throws ScriptException {
        //-(1+(4+51 +  2 )-3)+(6+8)
        // -2+ 1
        // 1 + 1
        //1+5-4
        String testExpr = "1+5-4";
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Object evalResult = engine.eval(testExpr);
        int calcResult = new Calculator().calculate(testExpr);
        System.out.println("evalResult: " + evalResult + " calcResult: " + calcResult);
        assert (Integer) evalResult == calcResult;
    }
}


class Calculator {
    private final Stack<Operation> stack = new Stack<>();
    private int count = 0;

    public int calculate(String s) {
        int leftPart = 0;
        boolean previousDig = false;
        while (count < s.length()) {
            char ch = s.charAt(count);
            count++;
            if (Character.isDigit(ch)) {
                if(previousDig) {
                    leftPart = new Concat(leftPart).perform(Character.getNumericValue(ch));
                } else {
                    leftPart = Character.getNumericValue(ch);
                }
            } else if (ch == '-') {
                stack.add(new Subtraction(leftPart));
            }else if (ch == '+') {
                if(stack.size() > 0) {
                    leftPart = stack.pop().perform(leftPart);
                }
                stack.add(new Addition(leftPart));
            } else if (ch == '(') {
                if (stack.empty()) {
                    leftPart = calculate(s);
                } else {
                    leftPart = stack.pop().perform(calculate(s));
                }
            }
            if((Character.isSpaceChar(ch) || ch == ')') && previousDig && !stack.empty()) {
                leftPart = stack.pop().perform(leftPart);
            }
            previousDig = Character.isDigit(ch);
            if (ch == ')') {
                return leftPart;
            }
        }
        return stack.isEmpty() ? leftPart :  stack.pop().perform(leftPart);
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

class Concat implements Operation {
    int leftPart;

    public Concat(int leftPart) {
        this.leftPart = leftPart;
    }

    @Override
    public int perform(int rightPart) {
        return Integer.parseInt(String.valueOf(leftPart) + rightPart);
    }

    @Override
    public String toString() {
        return "Concat{" +
                "leftPart=" + leftPart +
                '}';
    }
}