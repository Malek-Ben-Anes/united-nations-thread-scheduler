package com.debiotech.scheduler.v1.tasks;

public class ScheduledTaskD extends ScheduledTask {

    public ScheduledTaskD(int initialDelay, int interval) {
        super("D", () -> System.out.print("D"), initialDelay, interval);
    }

}
