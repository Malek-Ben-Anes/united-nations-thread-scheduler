package com.debiotech.scheduler.tasks;

import java.util.List;

public class ScheduledTaskFactory {

    private static final int TASK_PERIOD_1S = 1;
    private static final int TASK_PERIOD_5S = 5;
    private static final int TASK_PERIOD_10S = 10;

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
        return new TaskA(1, 0, TASK_PERIOD_1S);
    }

    public ScheduledTask createTaskB() {
        return new TaskB(2, 1, TASK_PERIOD_5S);
    }

    public ScheduledTask createTaskC() {
        return new TaskC(3, 2, TASK_PERIOD_5S);
    }

    public ScheduledTask createTaskD() {
        return new TaskD(4, 3, TASK_PERIOD_10S);
    }

    public ScheduledTask createTaskE() {
        return new TaskE(5, 4, TASK_PERIOD_10S);
    }
}
