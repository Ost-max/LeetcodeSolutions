package com.leetcode.concurrency;
import java.util.concurrent.*;
// https://www.codewars.com/kata/5b2b4836b6989d207700005b/train/java

public class ExecuteMeNTimes {


        class Action extends RecursiveAction {

            private int n;

            private Runnable action;

            public Action(Runnable action, int n) {
                this.action = action;
                this.n = n;
            }

            @Override
            protected void compute() {
                if(n <= 0 ) {
                    return;
                }
                Action subtask = new Action(action, --n);
                subtask.fork();
                action.run();
                subtask.join();
            }
        }


        public void execute(Runnable action, int nTimes) {
            ForkJoinPool executor = new ForkJoinPool(nTimes);
            Action forkAction = new Action(action, nTimes);
            executor.invoke(forkAction);
        }



/*  Easier solution:

    public class Worker {

        public void execute(Runnable action, int nTimes) {

            ExecutorService executorService = Executors.newFixedThreadPool(nTimes);

            for (int i = 0; i < nTimes; i++) {
                executorService.submit(action);
            }

            executorService.shutdown();

            try {
                executorService.awaitTermination(10, TimeUnit.DAYS);
            } catch(InterruptedException e) {}
        }
    }
*/

}
