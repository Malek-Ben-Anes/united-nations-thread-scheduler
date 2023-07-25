package com.debiotech.scheduler.v1.tasks;

import java.util.concurrent.Semaphore;

/**
 * A specific implementation of ScheduledTask representing Task B.
 */
public class ScheduledTaskE extends ScheduledTask {

    /**
     * Constructs a ScheduledTaskE with the given initial delay and interval.
     *
     * @param initialDelayInSeconds The delay in seconds before the first execution of Task E.
     * @param intervalInSeconds     The interval in seconds between subsequent executions of Task E.
     */
    public ScheduledTaskE(Semaphore semaphore, int initialDelayInSeconds, int intervalInSeconds) {
        super("E", () -> System.out.print("E"), semaphore, initialDelayInSeconds, intervalInSeconds);
    }

}
