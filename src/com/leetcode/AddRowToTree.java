package com.leetcode;

import java.util.TreeMap;

public class AddRowToTree {

    public static class Counter {
        private int i = 0;

        Counter(int i){
            this.i = i;
        }

        Counter increase() {
            i++;
            return this;
        }

        int get() {
            return i;
        }


    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
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

    public static void main(String ...args) {
    /*    Integer[] inputArr = new Integer[]{4,2,null,1,1,3,null,null,1};
       TreeNode tree = new AddRowToTree().readTreeLevelOrder(inputArr);*/

        TreeNode child1 =  new TreeNode(2);
        child1.left = new TreeNode(3);
        child1.right = new TreeNode(1);

        TreeNode tree = new TreeNode(4, child1, null);
        new AddRowToTree().addOneRow(tree, 1, 3);
        System.out.println(tree);
    }


    private boolean canCreateNode(Integer[] tree, int count) {
        return tree.length > count && tree[count] != null;
      }

    public void readTreePreOrder(TreeNode root, Integer[] tree, Counter counter) {
        if(root == null) {
            return;
        }
        counter.increase();
        root.left = canCreateNode(tree, counter.get()) ? new TreeNode(tree[counter.get()]) : null;
        counter.increase();
        root.right = canCreateNode(tree, counter.get()) ? new TreeNode(tree[counter.get()]) : null;
        readTreePreOrder(root.left, tree, counter);
        readTreePreOrder(root.right, tree, counter);
    }


    public TreeNode createTreeNode(Integer[] tree, int counter, int level) {
        return tree[counter] != null ? new TreeNode(tree[counter]) : null;
    }



  /*  public boolean readTreeLevelOrder(Integer[] tree, TreeNode node, Counter counter, int level) {
        if (node == null) {
            return false;
        }
        if (level == 1) {
            return true;
        }
        counter.increase();
        node.left = canCreateNode(tree, counter.get()) ? new TreeNode(tree[counter.get()]) : null;
        counter.increase();
        node.right = canCreateNode(tree, counter.get()) ? new TreeNode(tree[counter.get()]) : null;
        boolean left = readTreeLevelOrder(tree, node.left, counter, level - 1);
        boolean right = readTreeLevelOrder(tree, node.right, counter, level - 1);
        return left || right;
    }

    public TreeNode readTreeLevelOrder(Integer[] tree) {
        TreeNode root = new TreeNode(tree[0]);
        int level = 1;
        Counter counter = new Counter(0);
        while (readTreeLevelOrder(tree, root, counter, level)) {
            level++;
        }
        return root;
    }*/


    private void inOrderTraversal(TreeNode node, Integer[] tree, Counter counter) {
        if(node != null) {
            inOrderTraversal(node.left, tree, counter);

        }
    }

          /*      TreeNode node = new TreeNode(tree[0], new TreeNode(tree[1]), new TreeNode(tree[2]));
          TreeNode node = new TreeNode(tree[3], new TreeNode(tree[5]), new TreeNode(tree[6]));
          TreeNode node = new TreeNode(tree[4], new TreeNode(tree[7]), new TreeNode(tree[8]));
*/


    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if(depth == 1) {
            return new TreeNode(val, root, null);
        }
        checkAndAdd(root, val, depth, 2);
        return root;
    }


    private void checkAndAdd(TreeNode node, int val, int depth, int counter) {
        if(node == null) {
            return;
        }
        if(depth == counter) {
            TreeNode leftRow = new TreeNode(val, node.left, null);
            TreeNode rightRow = new TreeNode(val, null, node.right);
            node.left = leftRow;
            node.right = rightRow;
        } else if(depth > counter) {
            counter++;
            checkAndAdd(node.left, val, depth, counter);
            checkAndAdd(node.right, val, depth, counter);
        }

    }


}
