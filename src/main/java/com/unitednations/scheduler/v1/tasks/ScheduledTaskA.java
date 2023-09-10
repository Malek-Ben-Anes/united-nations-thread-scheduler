package com.unitednations.scheduler.v1.tasks;

/**
 * A specific implementation of ScheduledTask representing Task A.
 */
public class ScheduledTaskA extends ScheduledTask {

    /**
     * Constructs a ScheduledTaskA with the given initial delay and interval.
     * @param initialDelayInSeconds The delay in seconds before the first execution of Task A.
     * @param intervalInSeconds     The interval in seconds between subsequent executions of Task A.
     */
    public ScheduledTaskA(int initialDelayInSeconds, int intervalInSeconds) {
        super("A", () -> System.out.print("A "), initialDelayInSeconds, intervalInSeconds);
    }

}
