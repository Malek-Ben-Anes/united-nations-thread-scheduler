package com.debiotech.scheduler;

import com.debiotech.scheduler.service.ExecutionPlanLogger;
import com.debiotech.scheduler.v1.manager.ScheduledTaskManager;
import com.debiotech.scheduler.v1.tasks.ScheduledTaskFactory;
import com.debiotech.scheduler.v2.PriorityQueueTaskManager;

import java.util.Scanner;

public class DebiotechSchedulerApplication {

    private static final int MAX_CONCURRENT_TASKS = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            printMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after reading the integer

            switch (choice) {
                case 1:
                    executeScheduledTaskAlgorithm();
                    break;
                case 2:
                    launchPriorityQueueAlgorithm();
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private static void printMenu() {
        System.out.println("----------- Menu -----------");
        System.out.println("1. Execute scheduled task algorithm");
        System.out.println("2. Launch priority queue based algorithm");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void executeScheduledTaskAlgorithm() {
        System.out.println("\nScheduled task algorithm executed... \n");

        // self-scheduled task algorithm
        ScheduledTaskFactory taskFactory = new ScheduledTaskFactory();
        ExecutionPlanLogger executionPlanLogger = new ExecutionPlanLogger();

        ScheduledTaskManager taskScheduler = new ScheduledTaskManager(taskFactory, executionPlanLogger, MAX_CONCURRENT_TASKS);
        taskScheduler.execute();
    }

    private static void launchPriorityQueueAlgorithm() {
        System.out.println("\nPriority queue based algorithm launched... \n");

        // The priority queue based algorithm implementation
        PriorityQueueTaskManager taskManager = new PriorityQueueTaskManager();
        taskManager.execute();
    }

}
