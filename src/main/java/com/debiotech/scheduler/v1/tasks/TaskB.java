package com.debiotech.scheduler.v1.tasks;

public class TaskB extends ScheduledTask {

    public TaskB(int priority, int initialDelay, int interval) {
        super("B", () -> System.out.print("B"), priority, initialDelay, interval);
    }

}
