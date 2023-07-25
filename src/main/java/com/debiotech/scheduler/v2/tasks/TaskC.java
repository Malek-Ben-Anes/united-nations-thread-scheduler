package com.debiotech.scheduler.v2.tasks;

/**
 * An implementation of Task representing Task C.
 * Task C is a specific task with a name "C" and a given next execution time and interval.
 */
public class TaskC extends Task {

    /**
     * Constructs Task C with the given next execution time and interval.
     *
     * @param nextExecutionTime The next execution time of Task C (in seconds).
     * @param interval          The interval between consecutive executions of Task C (in seconds).
     */
    public TaskC(long nextExecutionTime, int interval) {
        super("C", nextExecutionTime, interval);
    }
}
