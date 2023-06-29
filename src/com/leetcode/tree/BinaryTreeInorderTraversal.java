package com.leetcode.tree;
//https://leetcode.com/explore/interview/card/top-interview-questions-medium/108/trees-and-graphs/786/

import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
public class BinaryTreeInorderTraversal {

    public static void main(String[] args) {
        var solution =   new BinaryTreeInorderTraversal();
        var case1 = solution.case1();
        System.out.println(solution.inorderTraversal(case1).get(2));
    }

    public List<Integer> inorderTraversal(TreeNode node) {
        var result = new LinkedList<Integer>();
        if(node == null) {
            return result;
        }
        if (node.left != null) {
            result.addAll(inorderTraversal(node.left));
        }
            result.add(node.val);

        if (node.right != null) {
            result.addAll(inorderTraversal(node.right));
        }
        return result;
    }

    public TreeNode case1() {
        var leef1 = new TreeNode(4);
        var leef2 = new TreeNode(5);
        var leftNode1 = new TreeNode(2, leef1, leef2);

        var leef3 = new TreeNode(6);
        var leef4 = new TreeNode(7);
        var rightNode1 = new TreeNode(3, leef3, leef4);
        return new TreeNode(1, leftNode1, rightNode1);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

}
