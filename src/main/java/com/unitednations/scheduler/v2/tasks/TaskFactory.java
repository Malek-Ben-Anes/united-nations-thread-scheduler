package com.unitednations.scheduler.v2.tasks;

import java.util.List;

/**
 * A factory class for creating different types of tasks used in the ScheduledTaskManager.
 */
public class TaskFactory {

    private static final int TASK_INTERVAL_1S = 1;
    private static final int TASK_INTERVAL_5S = 5;
    private static final int TASK_INTERVAL_10S = 10;

    /**
     * Creates a list of all tasks used in the ScheduledTaskManager.
     *
     * @return A list of all tasks.
     */
    public List<Task> createAllTasks() {
        return List.of(
                createTaskA(),
                createTaskB(),
                createTaskC(),
                createTaskD(),
                createTaskE()
        );
    }

    /**
     * Creates Task A with the specified next execution time and interval.
     *
     * @return Task A.
     */
    public Task createTaskA() {
        return new TaskA(1, TASK_INTERVAL_1S);
    }

    /**
     * Creates Task B with the specified next execution time and interval.
     *
     * @return Task B.
     */
    public Task createTaskB() {
        return new TaskB(2, TASK_INTERVAL_5S);
    }

    /**
     * Creates Task C with the specified next execution time and interval.
     *
     * @return Task C.
     */
    public Task createTaskC() {
        return new TaskC(3, TASK_INTERVAL_5S);
    }

    /**
     * Creates Task D with the specified next execution time and interval.
     *
     * @return Task D.
     */
    public Task createTaskD() {
        return new TaskD(4, TASK_INTERVAL_10S);
    }

    /**
     * Creates Task E with the specified next execution time and interval.
     *
     * @return Task E.
     */
    public Task createTaskE() {
        return new TaskE(5, TASK_INTERVAL_10S);
    }
}
