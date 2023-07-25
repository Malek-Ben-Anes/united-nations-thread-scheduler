package com.debiotech.scheduler.v1.tasks;

public class ScheduledTaskA extends ScheduledTask {

    public ScheduledTaskA(int initialDelay, int interval) {
        super("A", () -> System.out.print("A"), initialDelay, interval);
    }

}
