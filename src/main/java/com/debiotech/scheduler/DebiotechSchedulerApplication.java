package com.debiotech.scheduler;

import com.debiotech.scheduler.v1.service.ExecutionPlanLogger;
import com.debiotech.scheduler.v1.service.ScheduledTaskManager;
import com.debiotech.scheduler.v1.tasks.ScheduledTaskFactory;

public class DebiotechSchedulerApplication {

    private static final int MAX_CONCURRENT_TASKS = 2;

    public static void main(String[] args) {
        // V1
        if (true) {

        }
        // V2
        if (true) {

        }


        ScheduledTaskFactory taskFactory = new ScheduledTaskFactory();
        ExecutionPlanLogger executionPlanLogger = new ExecutionPlanLogger();

        ScheduledTaskManager taskScheduler = new ScheduledTaskManager(taskFactory, executionPlanLogger, MAX_CONCURRENT_TASKS);
        taskScheduler.invokeAllTasks();


        // ChatEndpoint ch = new ChatEndpoint();
    }

}
