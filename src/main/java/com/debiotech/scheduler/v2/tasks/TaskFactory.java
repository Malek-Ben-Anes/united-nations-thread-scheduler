package com.debiotech.scheduler.v2.tasks;

import com.debiotech.scheduler.v1.tasks.ScheduledTask;

import java.util.List;
import java.util.PriorityQueue;

public class TaskFactory {

    private static final int TASK_INTERVAL_1S = 1;
    private static final int TASK_INTERVAL_5S = 5;
    private static final int TASK_INTERVAL_10S = 10;

    // ont to overengineer
    public List<Task> createAllTasks() {
        return List.of(
                createTaskA(),
                createTaskB(),
                createTaskC(),
                createTaskD(),
                createTaskE()
        );
    }

    public Task createTaskA() {
        return new TaskA(0, TASK_INTERVAL_1S);
    }

    public Task createTaskB() {
        return new TaskB(1, TASK_INTERVAL_5S);
    }

    public Task createTaskC() {
        return new TaskC(2, TASK_INTERVAL_5S);
    }

    public Task createTaskD() {
        return new TaskD(3, TASK_INTERVAL_10S);
    }

    public Task createTaskE() {
        return new TaskD(4, TASK_INTERVAL_10S);
    }
}
