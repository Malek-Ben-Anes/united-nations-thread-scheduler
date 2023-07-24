package com.debiotech.scheduler.tasks;

public class TaskD extends ScheduledTask {

    public TaskD(int priority, int initialDelay, int interval) {
        super("D", () -> System.out.print("D"), priority, initialDelay, interval);
    }

}
