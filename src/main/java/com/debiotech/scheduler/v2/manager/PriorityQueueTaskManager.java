package com.debiotech.scheduler.v2.manager;

import com.debiotech.scheduler.service.ExecutionPlanLogger;
import com.debiotech.scheduler.v2.tasks.Task;
import com.debiotech.scheduler.v2.tasks.TaskFactory;

import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A Task Manager that uses a priority queue to schedule and execute tasks.
 * The PriorityQueueTaskManager is responsible for managing the execution of tasks
 * using a priority queue data structure. The priority queue ensures that tasks with
 * earlier next execution times are executed first, allowing the Task Manager to prioritize
 * time-sensitive tasks and improve overall task execution efficiency.
 */
public class PriorityQueueTaskManager {

    // Execution interval in milliseconds. Determines how often the Task Manager checks for tasks to execute.
    private static final int EXECUTION_INTERVAL_IN_MS = 1000;

    // Number of core threads in the thread pool for task execution.
    private static final int CORE_THREAD_POOL_SIZE = 5;

    private final TaskFactory taskFactory; // Factory to create different types of tasks.
    private final ExecutionPlanLogger executionPlanLogger; // Logger to log the execution plan of tasks.
    private final ScheduledExecutorService scheduler; // Executor service to schedule task execution.

    // Current execution time in seconds. Used for determining task readiness for execution.
    private long currentExecutionTimeInSeconds = 1;

    /**
     * Constructs a PriorityQueueTaskManager with the provided TaskFactory and ExecutionPlanLogger.
     * The TaskFactory is responsible for creating different types of tasks used by the Task Manager.
     * The ExecutionPlanLogger is used to log the execution plan of tasks for monitoring and analysis purposes.
     *
     * @param taskFactory         The TaskFactory used to create different types of tasks.
     * @param executionPlanLogger The ExecutionPlanLogger used to log the execution plan of tasks.
     */
    public PriorityQueueTaskManager(TaskFactory taskFactory, ExecutionPlanLogger executionPlanLogger) {
        this.taskFactory = taskFactory;
        this.executionPlanLogger = executionPlanLogger;
        this.scheduler = Executors.newScheduledThreadPool(CORE_THREAD_POOL_SIZE);
    }

    /**
     * Executes the tasks using a priority queue and a scheduled executor service.
     * The tasks are executed at the specified interval (EXECUTION_INTERVAL_IN_MS).
     * The PriorityQueueTaskManager continuously checks for ready tasks based on their
     * next execution times and executes them promptly.
     */
    public void execute() {
        PriorityQueue<Task> taskQueue = createTasksPriorityQueue();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        // Print a header for the execution plan log.
        System.out.println("Time (sec) -> Tasks");

        scheduler.scheduleAtFixedRate(() -> {
            // Print the current execution time to the console.
            System.out.print("\n" + currentExecutionTimeInSeconds + " -> ");

            // Execute the first two tasks with the highest priority from the priority queue.
            Task task1 = taskQueue.poll();
            Task task2 = taskQueue.poll();

            // Check if task1 is not null (queue is not empty).
            if (task1 != null) {
                // Check if task1 is ready for execution based on its next execution time.
                if (task1.isReadyToExecute(currentExecutionTimeInSeconds)) {
                    // Execute task1 and log its execution plan.
                    task1.execute();
                    task1.reSchedule();
                    executionPlanLogger.addTask(currentExecutionTimeInSeconds, task1.getName());
                }
                taskQueue.offer(task1); // Reinsert the task into the queue.
            }

            // Check if task2 is not null (queue may have only one task left).
            if (task2 != null) {
                // Check if task2 is ready for execution based on its next execution time.
                if (task2.isReadyToExecute(currentExecutionTimeInSeconds)) {
                    // Execute task2 and update its next execution time.
                    task2.execute();
                    task2.reSchedule();
                }
                taskQueue.offer(task2); // Reinsert the task into the queue.
            }

            // Increment the current execution time for the next iteration.
            currentExecutionTimeInSeconds++;
        }, 0, EXECUTION_INTERVAL_IN_MS, TimeUnit.MILLISECONDS);
    }

    /**
     * Creates a priority queue of tasks based on their next execution times.
     * The tasks are obtained from the TaskFactory and ordered in the priority queue.
     * The priority queue ensures that tasks with earlier next execution times are at the front of the queue,
     * allowing the Task Manager to execute the most time-sensitive tasks first.
     *
     * @return A priority queue of tasks ordered by their next execution times.
     */
    private PriorityQueue<Task> createTasksPriorityQueue() {
        // Create a new priority queue to store the tasks.
        PriorityQueue<Task> taskQueue = new PriorityQueue<>();

        // Get a list of all tasks from the TaskFactory.
        List<Task> allTasks = taskFactory.createAllTasks();

        // Iterate through each task and add it to the priority queue.
        // The priority queue automatically orders the tasks based on their next execution times.
        for (Task task : allTasks) {
            taskQueue.offer(task);
        }

        // Return the priority queue containing the tasks ordered by their next execution times.
        return taskQueue;
    }
}
