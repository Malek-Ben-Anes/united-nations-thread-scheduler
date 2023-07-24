package com.debiotech.scheduler.v2;

import java.util.PriorityQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RandomTaskExecutor {
    private static final int EXECUTION_INTERVAL_MS = 1000;

    private static int step = 1;

    public static void main(String[] args) {
        PriorityQueue<Task> taskQueue = createTasksPriorityQueue();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        System.out.println("Time (sec) -> Tasks");

        scheduler.scheduleAtFixedRate(() -> {
            System.out.print("\n" + step + " -> ");

            Task task1 = taskQueue.poll();
            Task task2 = taskQueue.poll();

            if (task1 != null && task1.getPriority() <= step) {
                task1.execute();
                task1.schedule();
                taskQueue.offer(task1); // Reinsert the task to maintain its priority
            } else {
                taskQueue.offer(task1); // Reinsert the task to maintain its priority
            }

            if (task2 != null && task2.getPriority() <= step) {
                task2.execute();
                task2.schedule();
                taskQueue.offer(task2); // Reinsert the task to maintain its priority
            } else {
                taskQueue.offer(task2); // Reinsert the task to maintain its priority
            }

            step++;
        }, 0, EXECUTION_INTERVAL_MS, TimeUnit.MILLISECONDS);
    }

    private static PriorityQueue<Task> createTasksPriorityQueue() {
        PriorityQueue<Task> taskQueue = new PriorityQueue<>((t1, t2) -> Integer.compare(t1.getPriority(), t2.getPriority()));
        taskQueue.offer(new Task("A ", 1, 1));
        taskQueue.offer(new Task("B ", 2, 5));
        taskQueue.offer(new Task("C ", 3, 5));
        taskQueue.offer(new Task("D ", 4, 10));
        taskQueue.offer(new Task("E ", 5, 10));
        return taskQueue;
    }

    static class Task {
        private final String name;
        private int priority;
        public final int period;

        Task(String name, int priority, int period) {
            this.name = name;
            this.priority = priority;
            this.period = period;
        }

        void execute() {
            System.out.print(name);
        }

        int getPriority() {
            return priority;
        }

        void schedule() {
            this.priority += this.period;
        }
    }
}