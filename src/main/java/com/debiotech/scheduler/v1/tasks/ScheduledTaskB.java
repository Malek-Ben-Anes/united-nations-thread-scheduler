package com.debiotech.scheduler.v1.tasks;

import java.util.concurrent.Semaphore;

/**
 * A specific implementation of ScheduledTask representing Task B.
 */
public class ScheduledTaskB extends ScheduledTask {

    /**
     * Constructs a ScheduledTaskB with the given initial delay and interval.
     *
     * @param initialDelayInSeconds The delay in seconds before the first execution of Task B.
     * @param intervalInSeconds     The interval in seconds between subsequent executions of Task B.
     */
    public ScheduledTaskB(int initialDelayInSeconds, int intervalInSeconds) {
        super("B", () -> System.out.print("B "), initialDelayInSeconds, intervalInSeconds);
    }

}
