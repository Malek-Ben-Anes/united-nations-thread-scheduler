package com.debiotech.scheduler.v2.tasks;

import java.util.Objects;

/**
 * An abstract class representing a task to be executed by the ScheduledTaskManager.
 * This class provides a base implementation for tasks with a name, next execution time, and interval.
 * Subclasses should extend this class and override the 'execute()' method to define the specific task behavior.
 */
public abstract class Task implements Comparable<Task> {

    protected final String name;
    protected long nextExecutionTime; // Represents the next execution time in seconds.
    protected final int interval; // Interval between consecutive executions of the task (in seconds).

    /**
     * Constructs a Task with the given name, next execution time, and interval.
     *
     * @param name              The name of the task.
     * @param nextExecutionTime The next execution time of the task (in seconds).
     * @param interval          The interval between consecutive executions of the task (in seconds).
     */
    public Task(String name, long nextExecutionTime, int interval) {
        this.name = name;
        this.nextExecutionTime = nextExecutionTime;
        this.interval = interval;
    }

    /**
     * Executes the task. Subclasses should override this method to define the specific task behavior.
     */
    public void execute() {
        System.out.print(name);
    }

    /**
     * Checks if the task is ready to be executed based on the current time.
     *
     * @param currentExecutionTime The current execution time in seconds.
     * @return true if the task is ready to be executed, false otherwise.
     */
    public boolean isReadyToExecute(long currentExecutionTime) {
        return currentExecutionTime >= nextExecutionTime;
    }

    /**
     * Updates the next execution time of the task based on the interval.
     * This method is called after each execution to schedule the next execution.
     */
    public void reSchedule() {
        nextExecutionTime += interval;
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
     * Gets the next execution time of the task.
     *
     * @return The next execution time of the task (in seconds).
     */
    public long getNextExecutionTime() {
        return nextExecutionTime;
    }

    /**
     * Gets the interval time in second of the task.
     *
     * @return The interval time of the task (in seconds).
     */
    public int getInterval() {
        return interval;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(Task other) {
        // First, compare based on nextExecutionTime
        int result = Long.compare(this.nextExecutionTime, other.nextExecutionTime);

        // If the nextExecutionTime is the same, compare based on task names
        if (result == 0) {
            result = this.name.compareTo(other.name);
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return nextExecutionTime == task.nextExecutionTime && interval == task.interval && Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nextExecutionTime, interval);
    }
}
