package com.leetcode.concurrency.diningphilosophers;

import java.util.concurrent.Semaphore;

class DiningPhilosophersSimpleSolution {



    public DiningPhilosophersSimpleSolution() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                                        Runnable pickLeftFork,
                                        Runnable pickRightFork,
                                        Runnable eat,
                                        Runnable putLeftFork,
                                        Runnable putRightFork) throws InterruptedException {
        int rightFork  = philosopher == 0 ? 4 : philosopher - 1;
        int leftFork = philosopher;

        SemaphoreHolder.semaphores[rightFork].acquire();
        System.out.printf("Philosopher: %s ", philosopher);
        pickRightFork.run();

        SemaphoreHolder.semaphores[leftFork].acquire();
        System.out.printf("Philosopher: %s ", philosopher);
        pickLeftFork.run();

        System.out.printf("Philosopher: %s ", philosopher);
        eat.run();


        System.out.printf("Philosopher: %s ", philosopher);
        putRightFork.run();
        SemaphoreHolder.semaphores[rightFork].release();

        System.out.printf("Philosopher: %s ", philosopher);
        putLeftFork.run();
        SemaphoreHolder.semaphores[leftFork].release();
    }

    static class SemaphoreHolder {


        static final Semaphore[] semaphores = new Semaphore[]{
                new Semaphore(1),
                new Semaphore(1),
                new Semaphore(1),
                new Semaphore(1),
                new Semaphore(1)
        };
    }
}

