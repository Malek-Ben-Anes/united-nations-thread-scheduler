package com.debiotech.scheduler.v1.tasks;

public class TaskC  extends ScheduledTask {

    public TaskC(int priority, int initialDelay, int interval) {
        super("C", () -> System.out.print("C"), priority, initialDelay, interval);
    }

}
