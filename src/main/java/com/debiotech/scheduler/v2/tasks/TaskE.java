package com.debiotech.scheduler.v2.tasks;

/**
 * An implementation of Task representing Task E.
 * Task E is a specific task with a name "E" and a given next execution time and interval.
 */
public class TaskE extends Task {

    /**
     * Constructs Task E with the given next execution time and interval.
     *
     * @param nextExecutionTime The next execution time of Task E (in seconds).
     * @param interval          The interval between consecutive executions of Task e (in seconds).
     */
    public TaskE(long nextExecutionTime, int interval) {
        super("E", nextExecutionTime, interval);
    }
}
