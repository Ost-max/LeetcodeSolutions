package com.leetcode.concurrency.diningphilosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class DiningPhilosophersRunner {

   static class  Philosopher extends Thread {

       final private int id;
       final DiningPhilosophersWaiterSolution diningPhilosopher;

       public Philosopher(DiningPhilosophersWaiterSolution diningPhilosopher, int id) {
           this.diningPhilosopher = diningPhilosopher;
           this.id = id;
           this.setName("Philosopher " + id);
       }


       public void run(){
           try {
               System.out.println("Thread " + id + " started");
               diningPhilosopher.wantsToEat(id,
                       () -> System.out.println(id + ": Pick right fork"),
                       () -> System.out.println(id + ": Pick left fork"),
                       () -> {
                           try {
                               Thread.sleep(500);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                           System.out.println(id + ": eat");
                       },
                       () -> System.out.println(id + ": Put left fork"),
                       () -> System.out.println(id + ": Put right fork")
                       );
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }

   }

    public static void main(String ...args){
        DiningPhilosophersWaiterSolution diningPhilosopher = new DiningPhilosophersWaiterSolution();

        new Philosopher(diningPhilosopher, 2).start();
        new Philosopher(diningPhilosopher, 0).start();
        new Philosopher(diningPhilosopher, 4).start();
        new Philosopher(diningPhilosopher, 3).start();
        new Philosopher(diningPhilosopher, 1).start();
        new Philosopher(diningPhilosopher, 0).start();
        new Philosopher(diningPhilosopher, 4).start();
    }


            /*executorService.execute(() -> {
            try {
                diningPhilosopher.wantsToEat(2, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            try {
                diningPhilosopher.wantsToEat(1, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/

}
