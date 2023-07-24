package com.debiotech.scheduler.tasks;

public class TaskB extends ScheduledTask {

    public TaskB(int priority, int initialDelay, int interval) {
        super("B", () -> System.out.print("B"), priority, initialDelay, interval);
    }

}
