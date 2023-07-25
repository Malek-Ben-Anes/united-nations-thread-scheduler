package com.debiotech.scheduler.v1.tasks;

import java.util.concurrent.Semaphore;

/**
 * A specific implementation of ScheduledTask representing Task A.
 */
public class ScheduledTaskA extends ScheduledTask {

    /**
     * Constructs a ScheduledTaskA with the given initial delay and interval.
     *
     * @param semaphore             The semaphore required to orchestrate Task A with other tasks.
     * @param initialDelayInSeconds The delay in seconds before the first execution of Task A.
     * @param intervalInSeconds     The interval in seconds between subsequent executions of Task A.
     */
    public ScheduledTaskA(Semaphore semaphore, int initialDelayInSeconds, int intervalInSeconds) {
        super("A", () -> System.out.print("A"), semaphore, initialDelayInSeconds, intervalInSeconds);
    }

}
