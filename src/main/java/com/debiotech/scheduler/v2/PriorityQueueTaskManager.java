package com.debiotech.scheduler.v2;

import com.debiotech.scheduler.v2.tasks.Task;
import com.debiotech.scheduler.v2.tasks.TaskFactory;

import java.util.PriorityQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// flexible group of plan...
//
public class PriorityQueueTaskManager {

    private static final int EXECUTION_INTERVAL_IN_MS = 1000;
    private static TaskFactory taskFactory = new TaskFactory();

    private int timeElapsedInSeconds = 1;

    public void execute() {
        PriorityQueue<Task> taskQueue = createTasksPriorityQueue();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        System.out.println("Time (sec) -> Tasks");

        // how to do when task more than more 1 second execution.
        scheduler.scheduleAtFixedRate(() -> {
            System.out.print("\n" + timeElapsedInSeconds + " -> ");

            Task task1 = taskQueue.poll();
            Task task2 = taskQueue.poll();

            if (task1 != null) {
                if (task1.getPriority() <= timeElapsedInSeconds) {
                    task1.execute();
                    task1.reSchedule();
                }
                taskQueue.offer(task1); // Reinsert the task to maintain its priority
            }

            if (task2 != null) {
                if (task2.getPriority() <= timeElapsedInSeconds) {
                    task2.execute();
                    task2.reSchedule();
                }
                taskQueue.offer(task2); // Reinsert the task to maintain its priority
            }

            timeElapsedInSeconds++;
        }, 0, EXECUTION_INTERVAL_IN_MS, TimeUnit.MILLISECONDS);
    }

    /**
     * Implements first-in-first-out logic.
     */
//    @Override
//    public int compareTo(ScheduledTask other) {
//        return this.priority.compareTo(other.priority);
//    }

    private static PriorityQueue<Task> createTasksPriorityQueue() {
        PriorityQueue<Task> taskQueue = new PriorityQueue<>((t1, t2) -> Integer.compare(t1.getPriority(), t2.getPriority()));
        taskFactory.createAllTasks().forEach(task -> taskQueue.offer(task));
        return taskQueue;
    }
}