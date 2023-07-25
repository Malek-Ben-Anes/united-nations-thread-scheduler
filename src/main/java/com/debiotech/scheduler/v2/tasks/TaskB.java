package com.debiotech.scheduler.v2.tasks;

/**
 * An implementation of Task representing Task B.
 * Task B is a specific task with a name "B" and a given next execution time and interval.
 */
public class TaskB extends Task {

    /**
     * Constructs Task B with the given next execution time and interval.
     *
     * @param nextExecutionTime The next execution time of Task B (in seconds).
     * @param interval          The interval between consecutive executions of Task B (in seconds).
     */
    public TaskB(long nextExecutionTime, int interval) {
        super("B", nextExecutionTime, interval);
    }
}
