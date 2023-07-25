package com.debiotech.scheduler.v1.tasks;

public class ScheduledTaskC extends ScheduledTask {

    public ScheduledTaskC(int initialDelay, int interval) {
        super("C", () -> System.out.print("C"), initialDelay, interval);
    }

}
