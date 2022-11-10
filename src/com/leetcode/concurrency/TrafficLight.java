package com.leetcode.concurrency;

import java.util.concurrent.*;

class TrafficLight {

    final ScheduledExecutorService sheduler = Executors.newScheduledThreadPool(1);
    final Semaphore roadA = new Semaphore(0);


    private volatile int currentRoad = 1;

    final CountDownLatch traficLightController = new CountDownLatch(3);


    public TrafficLight() {
    }

    public void carArrived(
            int carId,           // ID of the car
            int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
            int direction,       // Direction of the car
            Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
            Runnable crossCar    // Use crossCar.run() to make car cross the intersection
    ) throws InterruptedException  {
        System.out.println("carID: " + carId + "   roadId: " + roadId + "   currentRoad: " + currentRoad);
        if(currentRoad  != roadId) {
            traficLightController.await();
            System.out.println("awaitFinished");
            currentRoad = roadId;
            turnGreen.run();
            crossCar.run();
        }
        crossCar.run();
        traficLightController.countDown();
    }
}