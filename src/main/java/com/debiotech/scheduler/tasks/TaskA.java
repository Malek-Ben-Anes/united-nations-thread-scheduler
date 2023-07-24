package com.debiotech.scheduler.tasks;

public class TaskA extends ScheduledTask {

    public TaskA(int priority, int initialDelay, int interval) {
        super("A", () -> System.out.print("A"), priority, initialDelay, interval);
    }

}
