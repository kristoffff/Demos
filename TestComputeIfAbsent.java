package com.ccit.spark.java;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TestComputeIfAbsent {

    private final static ConcurrentHashMap<Integer, Queue<Integer>> map = new ConcurrentHashMap();
    private final static AtomicInteger ai = new AtomicInteger(0);
    private final static int NB_THREADS = Runtime.getRuntime().availableProcessors();
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ex = Executors.newFixedThreadPool(NB_THREADS);
        for (int i = 0; i < 10000; i++) {
            ex.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        ai.incrementAndGet();
                        Integer keyInt = new Integer((int) (1000 * Math.random()));
                        Integer valueInt = new Integer((int) (10_000_000 * Math.random()));
                        map.computeIfAbsent(keyInt, k -> new ConcurrentLinkedQueue<>()).add(valueInt);
                    }
                }
            });
        }
        ex.shutdown();
        ex.awaitTermination(1000,TimeUnit.SECONDS);
        System.out.println(ai);
        System.out.println(map.values().stream().map(Queue::size).collect(Collectors.summingInt(v -> (int) v)));

    }


}
