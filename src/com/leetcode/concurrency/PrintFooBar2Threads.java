package com.leetcode.concurrency;

public class PrintFooBar2Threads {
     // https://leetcode.com/problems/print-foobar-alternately/
    class FooBar {
        private int n;
        private boolean fooSeted;

        public FooBar(int n) {
            this.n = n;
        }

        public synchronized void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                while(fooSeted) {
                    wait();
                }
                printFoo.run();
                fooSeted = true;
                notify();
            }
        }

        public synchronized void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                while(!fooSeted) {
                    wait();
                }
                printBar.run();
                fooSeted = false;
                notify();
            }
        }
    }


}
