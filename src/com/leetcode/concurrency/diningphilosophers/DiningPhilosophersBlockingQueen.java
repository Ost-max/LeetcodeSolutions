package com.leetcode.concurrency.diningphilosophers;



import java.util.concurrent.*;


class DiningPhilosophersBlockingQueueSolution {

    final Waiter waiter = new Waiter();

    public DiningPhilosophersBlockingQueueSolution() {

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

    class Waiter {

        final ExecutorService executorService = Executors.newFixedThreadPool(1);
        private final BlockingQueue<Task> philosopherQueue = new LinkedBlockingQueue<>();
        final boolean[] availableForks = new boolean[]{
                true,
                true,
                true,
                true,
                true
        };

        public Waiter() {
            Runnable queueInspector = () -> {
                boolean active = true;
                while (active) {
                    try {
                        Task task = philosopherQueue.take();
                        System.out.println(task);
                        if (task != null) {
                            tryEat(task.getPhilosopher(),
                                    task.getPickLeftFork(),
                                    task.getPickRightFork(),
                                    task.getEat(),
                                    task.getPutRightFork(),
                                    task.getPutLeftFork());
                        } else {
                            active = false;
                            System.out.println(active);

                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            };
            this.executorService.execute(queueInspector);
        }

        public void tryEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat, Runnable putRight, Runnable putLeft) throws InterruptedException {
            int rightFork  = philosopher == 0 ? 4 : philosopher - 1;
            int leftFork = philosopher;
            synchronized (this) {
                if(availableForks[rightFork] && availableForks[leftFork]) {
                    availableForks[rightFork] = false;
                    availableForks[leftFork] = false;
                    eatAndFinish(philosopher, pickLeftFork, pickRightFork, eat, putRight, putLeft);
                } else {
                    philosopherQueue.add(new Task(philosopher, pickLeftFork, pickRightFork, eat, putRight, putLeft));
                }
            }
        }

        private void eatAndFinish(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat, Runnable putRight, Runnable putLeft) {
            pickRightFork.run();
            pickLeftFork.run();
            eat.run();
            putRight.run();
            putLeft.run();
            int rightFork  = philosopher == 0 ? 4 : philosopher - 1;
            int leftFork = philosopher;
            synchronized (this) {
                availableForks[rightFork] = true;
                availableForks[leftFork] = true;
            }
        }


    }

    class Task {

        private final int philosopher;
        private final Runnable pickLeftFork;
        private final Runnable pickRightFork;
        private final Runnable eat;
        private final Runnable putLeftFork;
        private final Runnable putRightFork;

        Task(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat, Runnable putLeftFork, Runnable putRightFork) {
            this.philosopher = philosopher;
            this.pickLeftFork = pickLeftFork;
            this.pickRightFork = pickRightFork;
            this.eat = eat;
            this.putLeftFork = putLeftFork;
            this.putRightFork = putRightFork;
        }


        public int getPhilosopher() {
            return philosopher;
        }

        public Runnable getPickLeftFork() {
            return pickLeftFork;
        }

        public Runnable getPickRightFork() {
            return pickRightFork;
        }

        public Runnable getEat() {
            return eat;
        }

        public Runnable getPutLeftFork() {
            return putLeftFork;
        }

        public Runnable getPutRightFork() {
            return putRightFork;
        }

    }
}




