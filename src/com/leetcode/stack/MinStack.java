package com.leetcode.stack;

//https://leetcode.com/problems/min-stack/
public class MinStack {

    public static void main(String[] args) {
        var minStack = new MinStack();
         //  ["MinStack","push","push","push","getMin","pop","top","getMin"]
        // [[],[-2],[0],[-3],[],[],[],[]]
        minStack.push(-2);
        System.out.println(minStack);
        minStack.push(0);
        System.out.println(minStack);
        minStack.push(-3);
        System.out.println(minStack);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

    @Override
    public String toString() {
        return "MinStack{" +
                "head=" + head +
                '}';
    }

    class Node {

        public Node(Node node, int val) {
            this.min = node != null ? Math.min(node.min, val) : val;
            this.previous = node;
            this.val = val;
        }

        Node previous;
        int val;
        int min;

        @Override
        public String toString() {
            return "Node{" +
                    "previous=" + previous +
                    ", val=" + val +
                    ", min=" + min +
                    '}';
        }
    }

    private Node head;

    public MinStack() {
    }

    public void push(int val) {
        head = new Node(head, val);
    }

    public void pop() {
        if(head != null) {
            head = head.previous;
        }
    }

    public int top() {
        return head != null ? head.val : 0;
    }

    public int getMin() {
        return head != null ? head.min : 0;
    }


}
