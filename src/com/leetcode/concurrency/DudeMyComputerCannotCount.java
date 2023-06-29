package com.leetcode.concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

//https://www.codewars.com/kata/58fa9898dfec0ef150000014/train/java
public class DudeMyComputerCannotCount {
    private final AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<String, Long> visitorCount = new ConcurrentHashMap<>();

    public void visit(String name) {
        counter.incrementAndGet();
        visitorCount.compute(name, (k, v) -> (v == null) ? 1L : ++v);
    }

    public long totalVisits() {
        return counter.get();
    }

    public long visitsBy(String name) {
        return visitorCount.getOrDefault(name, 0L);
    }
}
