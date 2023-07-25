package com.debiotech.scheduler.v1.tasks;

public class ScheduledTaskB extends ScheduledTask {

    public ScheduledTaskB(int initialDelay, int interval) {
        super("B", () -> System.out.print("B"), initialDelay, interval);
    }

}
