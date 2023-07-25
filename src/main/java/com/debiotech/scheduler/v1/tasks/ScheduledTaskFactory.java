package com.debiotech.scheduler.v1.tasks;

import java.util.List;

public class ScheduledTaskFactory {

    private static final int TASK_INTERVAL_1S = 1;
    private static final int TASK_INTERVAL_5S = 5;
    private static final int TASK_INTERVAL_10S = 10;

    // ont to overengineer
    public List<ScheduledTask> createAllTasks() {
        return List.of(
                createTaskA(),
                createTaskB(),
                createTaskC(),
                createTaskD(),
                createTaskE()
        );
    }

    public ScheduledTask createTaskA() {
        return new ScheduledTaskA(0, TASK_INTERVAL_1S);
    }

    public ScheduledTask createTaskB() {
        return new ScheduledTaskB(1, TASK_INTERVAL_5S);
    }

    public ScheduledTask createTaskC() {
        return new ScheduledTaskC(2, TASK_INTERVAL_5S);
    }

    public ScheduledTask createTaskD() {
        return new ScheduledTaskD(3, TASK_INTERVAL_10S);
    }

    public ScheduledTask createTaskE() {
        return new ScheduledTaskE(4, TASK_INTERVAL_10S);
    }
}
