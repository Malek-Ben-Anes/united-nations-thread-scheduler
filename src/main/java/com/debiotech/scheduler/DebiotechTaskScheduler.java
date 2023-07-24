package com.debiotech.scheduler;

import com.debiotech.scheduler.service.ExecutionPlanLogger;
import com.debiotech.scheduler.service.ScheduledTaskManager;
import com.debiotech.scheduler.tasks.ScheduledTaskFactory;

public class DebiotechTaskScheduler {

    private static final int MAX_CONCURRENT_TASKS = 2;

    public static void main(String[] args) {
        ScheduledTaskFactory taskFactory = new ScheduledTaskFactory();
        ExecutionPlanLogger executionPlanLogger = new ExecutionPlanLogger();

        ScheduledTaskManager taskScheduler = new ScheduledTaskManager(taskFactory, executionPlanLogger, MAX_CONCURRENT_TASKS);
        taskScheduler.invokeAllTasks();
    }

}
