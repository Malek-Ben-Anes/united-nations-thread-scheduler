package com.debiotech.scheduler.v2.tasks;

public abstract class Task {

    protected final String name;
    protected int priority;
    protected final int interval;

    public Task(String name, int priority, int interval) {
        this.name = name;
        this.priority = priority;
        this.interval = interval;
    }

    public void execute() {
        System.out.print(name);
    }

    public int getPriority() {
        return priority;
    }

    public void reSchedule() {
        this.priority += this.interval;
    }
}