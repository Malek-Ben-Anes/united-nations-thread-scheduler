package com.debiotech.scheduler.v2.tasks;

/**
 * An implementation of Task representing Task D.
 * Task D is a specific task with a name "D" and a given next execution time and interval.
 */
public class TaskD extends Task {

    /**
     * Constructs Task D with the given next execution time and interval.
     *
     * @param nextExecutionTime The next execution time of Task D (in seconds).
     * @param interval          The interval between consecutive executions of Task D (in seconds).
     */
    public TaskD(long nextExecutionTime, int interval) {
        super("D", nextExecutionTime, interval);
    }
}
