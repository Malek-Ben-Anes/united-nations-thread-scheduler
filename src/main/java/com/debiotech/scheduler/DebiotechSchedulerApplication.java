package com.debiotech.scheduler;

import com.debiotech.scheduler.v1.service.ExecutionPlanLogger;
import com.debiotech.scheduler.v1.service.ScheduledTaskManager;
import com.debiotech.scheduler.v1.tasks.ScheduledTaskFactory;

import java.util.Scanner;

public class DebiotechSchedulerApplication {

    private static final int MAX_CONCURRENT_TASKS = 2;

    public static void main(String[] args) {

        System.out.println("Two solutions were proposed to resolve the assessment, Choose solution '1' or solution '2'");

        Scanner scanner = new Scanner(System.in);
        String nameSurname = scanner.nextLine();

        System.out.println("Choosen solution: " + nameSurname);
        // V1
        if ("1".equals(nameSurname)) {
            ScheduledTaskFactory taskFactory = new ScheduledTaskFactory();
            ExecutionPlanLogger executionPlanLogger = new ExecutionPlanLogger();

            ScheduledTaskManager taskScheduler = new ScheduledTaskManager(taskFactory, executionPlanLogger, MAX_CONCURRENT_TASKS);
            taskScheduler.invokeAllTasks();
        }
        // V2
        if ("2".equals(nameSurname)) {

        }
    }

}
