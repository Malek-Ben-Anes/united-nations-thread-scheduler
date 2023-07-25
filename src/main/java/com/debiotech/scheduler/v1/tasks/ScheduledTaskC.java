package com.debiotech.scheduler.v1.tasks;

/**
 * A specific implementation of ScheduledTask representing Task C.
 */
public class ScheduledTaskC extends ScheduledTask {

    /**
     * Constructs a ScheduledTaskC with the given initial delay and interval.
     *
     * @param initialDelayInSeconds The delay in seconds before the first execution of Task C.
     * @param intervalInSeconds     The interval in seconds between subsequent executions of Task C.
     */
    public ScheduledTaskC(int initialDelayInSeconds, int intervalInSeconds) {
        super("C", () -> System.out.print("C"), initialDelayInSeconds, intervalInSeconds);
    }

}
