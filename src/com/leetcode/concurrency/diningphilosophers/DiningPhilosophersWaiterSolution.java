package com.leetcode.concurrency.diningphilosophers;

import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;


class DiningPhilosophersWaiterSolution {

    final Waiter waiter = new Waiter();

    public DiningPhilosophersWaiterSolution() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        waiter.tryEat(philosopher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork);
    }
}

class Philosopher {

    final int id;
    final Semaphore semaphore;

    Philosopher(int id, Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }
}

class Waiter {

    final Semaphore[] philosophers = new Semaphore[]{
            new Semaphore(0, true),
            new Semaphore(0, true),
            new Semaphore(0, true),
            new Semaphore(0, true),
            new Semaphore(0, true),
    };

    final boolean[] availableForks = new boolean[]{
            true,
            true,
            true,
            true,
            true
    };

    final Deque<Philosopher> philosopherQueue = new LinkedBlockingDeque<>();

    public void tryEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat, Runnable putRight, Runnable putLeft) throws InterruptedException {
        int rightFork = philosopher == 0 ? 4 : philosopher - 1;
        int leftFork = philosopher;
        boolean canProceed = false;
        synchronized (this) {
            if (availableForks[rightFork] && availableForks[leftFork]) {
                availableForks[rightFork] = false;
                availableForks[leftFork] = false;
                canProceed = true;
            }
        }
        if (canProceed) {
            eatAndFinish(philosopher, pickLeftFork, pickRightFork, eat, putRight, putLeft);
        } else {
            wait(philosopher, pickLeftFork, pickRightFork, eat, putRight, putLeft);
        }
    }

    private void eatAndFinish(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat, Runnable putRight, Runnable putLeft) {
        pickRightFork.run();
        pickLeftFork.run();
        eat.run();
        putRight.run();
        putLeft.run();
        int rightFork = philosopher == 0 ? 4 : philosopher - 1;
        int leftFork = philosopher;
        synchronized (this) {
            availableForks[rightFork] = true;
            availableForks[leftFork] = true;
            checkAndNotify();
        }
    }

    private synchronized void checkAndNotify() {
        for (Philosopher philosopher : philosopherQueue) {
            int rightFork = philosopher.id == 0 ? 4 : philosopher.id  - 1;
            int leftFork = philosopher.id ;
            if (philosopher.semaphore.hasQueuedThreads() && availableForks[rightFork] && availableForks[leftFork]) {
                philosopher.semaphore.release();
            }
        }
    }

    private void wait(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat, Runnable putRight, Runnable putLeft) throws InterruptedException {
        philosopherQueue.add(new Philosopher(philosopher, philosophers[philosopher]));
        philosophers[philosopher].acquire();
        tryEat(philosopher, pickLeftFork, pickRightFork, eat, putRight, putLeft);
    }
}