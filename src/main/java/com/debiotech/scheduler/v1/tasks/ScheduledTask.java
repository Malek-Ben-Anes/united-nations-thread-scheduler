package com.debiotech.scheduler.v1.tasks;

import java.util.TimerTask;
import java.util.concurrent.Semaphore;

/**
 * An abstract class representing a scheduled task that can be executed periodically by a timer.
 */
public abstract class ScheduledTask extends TimerTask {

    protected final String name;
    protected final Runnable command;
    protected final Semaphore semaphore;
    protected final int initialDelayInSeconds; // Initial delay before the first execution of the task (in seconds).
    protected final int intervalInSeconds; // Interval between subsequent executions of the task (in seconds).

    /**
     * Constructs a scheduled task with the given name, command, initial delay, and interval.
     *
     * @param name                 The name of the task.
     * @param command              The command to be executed.
     * @param semaphore            The semaphore to be shared and considered by different tasks.
     * @param initialDelayInSeconds The delay in seconds before the first execution of the task.
     * @param intervalInSeconds     The interval in seconds between subsequent executions of the task.
     */
    protected ScheduledTask(String name, Runnable command, Semaphore semaphore, int initialDelayInSeconds, int intervalInSeconds) {
        this.name = name;
        this.command = command;
        this.semaphore = semaphore;
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
            throw new RuntimeException("Scheduled task conflict occured!");
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

    /**
     * Gets the semaphore of the task.
     *
     * @return The semaphore of the task.
     */
    public Semaphore getSemaphore() {
        return semaphore;
    }
}