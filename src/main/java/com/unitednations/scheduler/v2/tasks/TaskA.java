package com.unitednations.scheduler.v2.tasks;

/**
 * An implementation of Task representing Task A.
 * Task A is a specific task with a name "A" and a given next execution time and interval.
 */
public class TaskA extends Task {

    /**
     * Constructs Task A with the given next execution time and interval.
     *
     * @param nextExecutionTime The next execution time of Task A (in seconds).
     * @param interval          The interval between consecutive executions of Task A (in seconds).
     */
    public TaskA(long nextExecutionTime, int interval) {
        super("A", nextExecutionTime, interval);
    }
}
