package com.debiotech.scheduler.v1.tasks;

import java.util.TimerTask;
import java.util.concurrent.Semaphore;

/**
 * An abstract class representing a scheduled task that can be executed periodically by a timer.
 */
public abstract class ScheduledTask extends TimerTask {

    // Only allow two tasks at most to run at a time.
    private static final int MAX_CONCURRENT_TASKS = 2;

    private static final Semaphore semaphore = new Semaphore(MAX_CONCURRENT_TASKS);

    protected final String name;
    protected final Runnable command;
    protected final int initialDelayInSeconds; // Initial delay before the first execution of the task (in seconds).
    protected final int intervalInSeconds; // Interval between subsequent executions of the task (in seconds).

    /**
     * Constructs a scheduled task with the given name, command, initial delay, and interval.
     *
     * @param name                 The name of the task.
     * @param command              The command to be executed.
     * @param initialDelayInSeconds The delay in seconds before the first execution of the task.
     * @param intervalInSeconds     The interval in seconds between subsequent executions of the task.
     */
    protected ScheduledTask(String name, Runnable command, int initialDelayInSeconds, int intervalInSeconds) {
        this.name = name;
        this.command = command;
        this.initialDelayInSeconds = initialDelayInSeconds;
        this.intervalInSeconds = intervalInSeconds;
    }

    @Override
    public void run() {
        try {
            // Acquire a permit to proceed
            semaphore.acquire();
            command.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
            // Handle the interrupted exception if needed.
        } finally {
            // Always release our permit
            semaphore.release();
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Gets the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the initial delay before the first execution of the task (in seconds).
     *
     * @return The initial delay in seconds.
     */
    public int getInitialDelayInSeconds() {
        return initialDelayInSeconds;
    }

    /**
     * Gets the interval between subsequent executions of the task (in seconds).
     *
     * @return The interval in seconds.
     */
    public int getIntervalInSeconds() {
        return intervalInSeconds;
    }
}