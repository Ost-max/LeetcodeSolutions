package com.leetcode.concurrency;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class FutureTests {

    final ExecutorService executorService = Executors.newFixedThreadPool(3);
    final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    public static void main(String ...args) {
        new FutureTests().test();

    }


    public void test() {
        Random generator = new Random();
        StringBuilder s = new StringBuilder(" ");
        for(int i = 0; i< 99999999; i++) {
            s.append(Character.toString(generator.nextInt(120)));
        }
        String result = s.append("|||").toString();
        Future<Integer> f = executorService.submit(() -> {
            int res = 0;
            String result2 = result.replaceAll("a", "b");
            for(char ch: result2.toCharArray()) {
                System.out.println(ch);
                if(ch == 'a') {
                    res++;
                }
            }
            return res;
         });
        try {

            scheduledExecutorService.schedule(() -> {
                System.out.println("cancel!!!");
                f.cancel(true);
                System.out.println("cancel!!! " + f.isCancelled());
            }, 1, TimeUnit.SECONDS);

            System.out.println(f.get());
            executorService.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
