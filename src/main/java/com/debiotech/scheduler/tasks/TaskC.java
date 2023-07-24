package com.debiotech.scheduler.tasks;

public class TaskC  extends ScheduledTask {

    public TaskC(int priority, int initialDelay, int interval) {
        super("C", () -> System.out.print("C"), priority, initialDelay, interval);
    }

}
