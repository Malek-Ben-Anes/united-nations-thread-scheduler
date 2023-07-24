package com.debiotech.scheduler.v1.tasks;

public class TaskE extends ScheduledTask {

    public TaskE(int priority, int initialDelay, int interval) {
        super("E", () -> System.out.print("E"), priority, initialDelay, interval);
    }

}
