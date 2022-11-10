package com.leetcode.concurrency;

import java.util.function.IntConsumer;

class FizzBuzz {

    private int n;
    private int i = 1;

    private Phase phase = Phase.NUM;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
        while (n > i) {
            while (phase != Phase.FIZZ && n >= i) {
                wait();
            }
            if(n < i) {
                break;
            }
            // System.out.println("fizz");

            printFizz.run();
            i++;
            phase = Phase.getPhase(i);
            notifyAll();
        }
    }

    // printBuzz.run() outputs "buzz".
    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        while (n > i) {
            while (phase != Phase.BUZZ && n >= i) {
                wait();
            }
            if(n < i) {
                break;
            }
            // System.out.println("buzz");

            printBuzz.run();
            i++;
            phase = Phase.getPhase(i);
            notifyAll();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (n > i) {
            while (phase != Phase.FIZZ_BUZZ && n >= i) {
                wait();
            }
            if(n < i) {
                break;
            }
            // System.out.println("fizzbuzz");

            printFizzBuzz.run();
            i++;
            phase = Phase.getPhase(i);
            notifyAll();

        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        while (n >=  i) {
            while (phase != Phase.NUM && n >= i)  {
                wait();
            }

            if(n < i) {
                break;
            }

            printNumber.accept(i);
            i++;
            phase = Phase.getPhase(i);
            notifyAll();
        }
    }

}


enum Phase {
    FIZZ, BUZZ, FIZZ_BUZZ, NUM;


    public static Phase getPhase(int i) {
        // System.out.print(" i =  " + i);
        // System.out.print("   i % 3 = " + i % 3);
        // System.out.print("   i % 5  =" + i % 5 + "\n");

        if (i % 3 == 0 && i % 5 == 0) {
            return FIZZ_BUZZ;
        } else if (i % 3 == 0) {
            return FIZZ;
        } else if (i % 5 == 0) {
            return BUZZ;
        }
        return NUM;
    }


}