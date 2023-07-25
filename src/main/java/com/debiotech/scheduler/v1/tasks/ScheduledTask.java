package com.debiotech.scheduler.v1.tasks;

import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public abstract class ScheduledTask extends TimerTask {

    private static final int MAX_CONCURRENT_TASKS = 2;

    // Only allow two tasks at most to run at a time.
    private static final Semaphore semaphore = new Semaphore(MAX_CONCURRENT_TASKS);

    protected final String name;
    protected final Runnable command;
    protected final int initialDelay;
    protected final int interval;

    protected ScheduledTask(String name, Runnable command, int initialDelay, int interval) {
        this.name = name;
        this.command = command;
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
