package com.debiotech.scheduler.v2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class App {

    public static void main(String[] args) throws Exception {
        TasksManager tasksManager = new TasksManager();
        AtomicInteger counter = new AtomicInteger();
        tasksManager.schedule(() -> runAction("Test 1 - " + counter.incrementAndGet(), TimeUnit.SECONDS.toMillis(2)), 1, 1, TimeUnit.SECONDS);
        tasksManager.schedule(() -> runAction("Test 2 - " + counter.incrementAndGet(), TimeUnit.SECONDS.toMillis(2)), 1, 1, TimeUnit.SECONDS);
        tasksManager.schedule(() -> runAction("Test 3 - " + counter.incrementAndGet(), TimeUnit.SECONDS.toMillis(2)), 1, 1, TimeUnit.SECONDS);

        tasksManager.schedule(() -> runAction("Test 4 - " + counter.incrementAndGet(), TimeUnit.SECONDS.toMillis(1)), 2, 6, TimeUnit.SECONDS);
        tasksManager.schedule(() -> runAction("Test 5 - " + counter.incrementAndGet(), TimeUnit.SECONDS.toMillis(1)), 2, 6, TimeUnit.SECONDS);
        tasksManager.schedule(() -> runAction("Test 6 - " + counter.incrementAndGet(), TimeUnit.SECONDS.toMillis(1)), 2, 6, TimeUnit.SECONDS);

        tasksManager.schedule(() -> runAction("Test 7 - " + counter.incrementAndGet(), TimeUnit.SECONDS.toMillis(2)), 1, 1, TimeUnit.SECONDS);

        System.out.println("All tasks are scheduled, sleeping...");
        Thread.sleep(TimeUnit.SECONDS.toMillis(30));
        System.out.println("Sleep complete, counter = " + counter + ", closing...");
        tasksManager.close();
    }

    private static void runAction(String text, long sleepMs) {
        try {
            Thread.sleep(sleepMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(text);
    }
}
