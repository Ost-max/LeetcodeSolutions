package com.leetcode.concurrency;

public class PrintInOrder {

//https://leetcode.com/problems/print-in-order/

    class Foo {

        int step = 1;


        public Foo() {
        }

        public void first() throws InterruptedException {
            System.out.println("first");
            synchronized(this) {
                step = 2;
                notifyAll();
            }

        }

        public synchronized void second() throws InterruptedException {
            while(step != 2) {
                wait();
            }
            System.out.println("second");
            step = 3;
            notifyAll();

        }

        public synchronized void third() throws InterruptedException {
            while(step != 3) {
                wait();
            }
            System.out.println("third");
            notifyAll();
        }

    }

    public static void main(String ...args) throws InterruptedException {
        new PrintInOrder().proceed();

    }

    public void proceed() throws InterruptedException {
        Foo foo = new Foo();
        new Thread(() -> {
            try {
                foo.second();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                foo.third();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            try {
                foo.first();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}




