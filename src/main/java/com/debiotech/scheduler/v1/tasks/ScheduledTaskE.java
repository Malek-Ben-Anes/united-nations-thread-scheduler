package com.debiotech.scheduler.v1.tasks;

public class ScheduledTaskE extends ScheduledTask {

    public ScheduledTaskE(int initialDelay, int interval) {
        super("E", () -> System.out.print("E"), initialDelay, interval);
    }

}
