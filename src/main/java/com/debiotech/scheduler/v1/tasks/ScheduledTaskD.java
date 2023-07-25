package com.debiotech.scheduler.v1.tasks;

import java.util.concurrent.Semaphore;

/**
 * A specific implementation of ScheduledTask representing Task D.
 */
public class ScheduledTaskD extends ScheduledTask {

    /**
     * Constructs a ScheduledTaskD with the given initial delay and interval.
     *
     * @param initialDelayInSeconds The delay in seconds before the first execution of Task D.
     * @param intervalInSeconds     The interval in seconds between subsequent executions of Task D.
     */
    public ScheduledTaskD(Semaphore semaphore, int initialDelayInSeconds, int intervalInSeconds) {
        super("D", () -> System.out.print("D"), semaphore, initialDelayInSeconds, intervalInSeconds);
    }

}
