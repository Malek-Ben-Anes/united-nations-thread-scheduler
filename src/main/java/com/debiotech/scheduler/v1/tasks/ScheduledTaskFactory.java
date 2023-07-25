package com.debiotech.scheduler.v1.tasks;

import java.util.List;

/**
 * A factory class to create different ScheduledTask instances.
 */
public class ScheduledTaskFactory {

    private static final int TASK_INTERVAL_1S = 1;
    private static final int TASK_INTERVAL_5S = 5;
    private static final int TASK_INTERVAL_10S = 10;

    /**
     * Creates all available ScheduledTask instances.
     *
     * @return A list of all available ScheduledTask instances.
     */
    public List<ScheduledTask> createAllTasks() {
        return List.of(
                createTaskA(),
                createTaskB(),
                createTaskC(),
                createTaskD(),
                createTaskE()
        );
    }

    /**
     * Creates a ScheduledTask instance for Task A.
     *
     * @return A ScheduledTask instance for Task A.
     */
    public ScheduledTask createTaskA() {
        return new ScheduledTaskA(0, TASK_INTERVAL_1S);
    }

    /**
     * Creates a ScheduledTask instance for Task B.
     *
     * @return A ScheduledTask instance for Task B.
     */
    public ScheduledTask createTaskB() {
        return new ScheduledTaskB(1, TASK_INTERVAL_5S);
    }

    /**
     * Creates a ScheduledTask instance for Task C.
     *
     * @return A ScheduledTask instance for Task C.
     */
    public ScheduledTask createTaskC() {
        return new ScheduledTaskC(2, TASK_INTERVAL_5S);
    }

    /**
     * Creates a ScheduledTask instance for Task D.
     *
     * @return A ScheduledTask instance for Task D.
     */
    public ScheduledTask createTaskD() {
        return new ScheduledTaskD(3, TASK_INTERVAL_10S);
    }

    /**
     * Creates a ScheduledTask instance for Task E.
     *
     * @return A ScheduledTask instance for Task E.
     */
    public ScheduledTask createTaskE() {
        return new ScheduledTaskE(4, TASK_INTERVAL_10S);
    }
}
