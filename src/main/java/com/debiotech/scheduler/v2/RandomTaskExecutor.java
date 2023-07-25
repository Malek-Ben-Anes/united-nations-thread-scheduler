package com.debiotech.scheduler.v2;

import com.debiotech.scheduler.v1.tasks.ScheduledTask;

import java.util.PriorityQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

// flexible group of plan...
//
public class RandomTaskExecutor {
    private static final int EXECUTION_INTERVAL_IN_MS = 1000;


    /**
     * Implements first-in-first-out logic.
     */
//    @Override
//    public int compareTo(ScheduledTask other) {
//        return this.priority.compareTo(other.priority);
//    }


    // task per second

    private static int counter = 1;

    public static void main(String[] args) {
        PriorityQueue<Task> taskQueue = createTasksPriorityQueue();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        System.out.println("Time (sec) -> Tasks");

        // how to do when task more than more 1 second execution.
        scheduler.scheduleAtFixedRate(() -> {
            System.out.print("\n" + counter + " -> ");

            Task task1 = taskQueue.poll();
            Task task2 = taskQueue.poll();

            if (task1 != null) {
                if (task1.getPriority() <= counter) {
                    task1.execute();
                    task1.reSchedule();
                }
                taskQueue.offer(task1); // Reinsert the task to maintain its priority
            }

            if (task2 != null) {
                if (task2.getPriority() <= counter) {
                    task2.execute();
                    task2.reSchedule();
                }
                taskQueue.offer(task2); // Reinsert the task to maintain its priority
            }

            counter++;
        }, 0, EXECUTION_INTERVAL_IN_MS, TimeUnit.MILLISECONDS);
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
        public final int interval;

        Task(String name, int priority, int interval) {
            this.name = name;
            this.priority = priority;
            this.interval = interval;
        }

        void execute() {
            System.out.print(name);
        }

        int getPriority() {
            return priority;
        }

        void reSchedule() {
            this.priority += this.interval;
        }
    }
}