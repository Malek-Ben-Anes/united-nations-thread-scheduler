package com.debiotech.scheduler.v1.tasks;

import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public abstract class ScheduledTask extends TimerTask implements Comparable<ScheduledTask>  {

    // Anly allow one person to sing at a time
    private static final Semaphore semaphore = new Semaphore(2);

    protected final String name;
    protected final Runnable command;
    protected final Integer priority;
    protected final int initialDelay;
    protected final int interval;

    protected ScheduledTask(String name, Runnable command, int priority, int initialDelay, int interval) {
        this.name = name;
        this.command = command;
        this.priority = priority;
        this.initialDelay = initialDelay;
        this.interval = interval;
    }

    @Override
    public void run() {
        try {
            // Aquire a permit to proceed
            semaphore.acquire();
            command.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Always release our permit
            semaphore.release();
        }
    }

    /**
     * Implements first-in-first-out logic.
     */
    @Override
    public int compareTo(ScheduledTask other) {
        return this.priority.compareTo(other.priority);
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public int getInitialDelay() {
        return initialDelay;
    }

    public int getInterval() {
        return interval;
    }
}
