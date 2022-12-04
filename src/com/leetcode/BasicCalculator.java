package com.leetcode;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;

// https://leetcode.com/problems/basic-calculator/
public class BasicCalculator {

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
}


class Calculator {
    private int count = 0;

    public int calculate(String s) {
        Expression expr = new Expression();
        while (count <  s.length()) {
            char ch = s.charAt(count);
            count++;
            if (isOperation(ch)) {
                expr.add(Operation.getInstance(ch));
            } else if (Character.isDigit(ch)) {
                expr.add(Character.getNumericValue(ch));
            } else if(ch == '(') {
                expr.add(calculate(s));
            } else if (ch == ')') {
                break;
            }
        }
        return expr.gerResult();
    }

    private boolean isOperation(char ch) {
        return "+-".indexOf(ch) > -1;
    }
    private boolean isCommit(char ch) {
        return ch == ')';
    }
}


interface Operation {
    int perform(int rightPart);

    int perform(int leftPart, int rightPart);

    void setLeftPart(int left);

    static Operation getInstance(char ch) {
        if(ch == '+') {
            return new Addition();
        } else if (ch == '-') {
            return new Subtraction();
        }
        return null;
    }

}


class Addition implements Operation {
    int leftPart;

    public Addition(int leftPart) {
        this.leftPart = leftPart;
    }

    @Override
    public void setLeftPart(int leftPart) {
        this.leftPart = leftPart;
    }

    public Addition() {
    }

    @Override
    public int perform(int rightPart) {
        return leftPart + rightPart;
    }

    @Override
    public int perform(int leftPart, int rightPart) {
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
    public void setLeftPart(int leftPart) {
        this.leftPart = leftPart;
    }
    public Subtraction() {
    }

    @Override
    public int perform(int rightPart) {
        return leftPart - rightPart;
    }

    @Override
    public int perform(int leftPart, int rightPart) {
        return leftPart - rightPart;
    }

    @Override
    public String toString() {
        return "Subtraction{" +
                "leftPart=" + leftPart +
                '}';
    }
}


class Expression {

    private Integer leftNum;
    private Integer rightNum;
    //    private int result;
    private Operation operation;
    private boolean inverse;

    public void add(int i) {
        if (operation == null) {
            leftNum = concat(leftNum, i);
        } else {
            rightNum = concat(rightNum, i);;
        }
    }

    private int concat(Integer leftPart, int rightPart) {
        return leftPart != null ? Integer.parseInt( leftPart + "" + rightPart)
                : Integer.parseInt(  "" + rightPart);
    }

    public void add(Operation operation) {
        if(this.operation != null) {
            this.perform(operation);
        }
        this.operation = operation;
    }


    private void perform(Operation operation) {
        if(rightNum != null) {
            leftNum = this.operation.perform(leftNum != null ? leftNum : 0, rightNum);
            rightNum = null;
        } else {
            leftNum = this.operation.perform(leftNum);
        }
    }
    public void inverse() {
        this.inverse = !inverse;
    }

    public int gerResult() {
        if (operation != null) {
            this.perform(operation);
        }
        return leftNum;
    }
}
