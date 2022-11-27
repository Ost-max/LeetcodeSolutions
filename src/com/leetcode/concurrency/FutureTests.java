package com.leetcode.concurrency;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;

public class FutureTests {

    final ExecutorService executorService = Executors.newFixedThreadPool(3);
    final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    public static void main(String ...args) {
        new FutureTests().test();

    }

    class PrimeGenerator implements Callable<BigInteger> {


        private volatile boolean cancelled;
        private volatile BigInteger p = BigInteger.ONE;


        @Override
        public BigInteger call() throws Exception {
            while (!cancelled) {
                p = p.nextProbablePrime();
            }
            return  p;
        }

        public BigInteger getCurrentPrime() {
            return p;
        }

        public void cancel() {
            this.cancelled = true;
        }
    }

    public void test() {
        PrimeGenerator primeGenerator = new PrimeGenerator();
        Future<BigInteger> f = executorService.submit(() -> BigInteger.ONE.add(BigInteger.ONE));
        try {
            System.out.println(f.get());
            executorService.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

//        scheduledExecutorService.schedule(() -> {
//            System.out.println(primeGenerator.getCurrentPrime());
//            f.cancel(true);
//            System.out.println("cancel!!! " + f.isCancelled());
//            System.out.println("done!!! " + f.isDone());
//            primeGenerator.cancel();
//            System.out.println(primeGenerator.getCurrentPrime());
//            System.out.println(primeGenerator.cancelled);
//            System.out.println(executorService.shutdownNow());
//            scheduledExecutorService.shutdown();
//        }, 1, TimeUnit.SECONDS);
    }

}
