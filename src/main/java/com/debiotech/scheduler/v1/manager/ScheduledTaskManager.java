package com.debiotech.scheduler.v1.manager;

import com.debiotech.scheduler.service.ExecutionPlanLogger;
import com.debiotech.scheduler.v1.tasks.ScheduledTask;
import com.debiotech.scheduler.v1.tasks.ScheduledTaskFactory;
import com.debiotech.scheduler.v1.tasks.ScheduledTaskA;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A manager that schedules and executes the registered tasks using a ScheduledExecutorService.
 */
public class ScheduledTaskManager {

    // Only allow two tasks at most to run at a time.
    public static final int MAX_CONCURRENT_TASKS = 2;
    private static final int CORE_THREAD_POOL_SIZE = 5;

    private final ScheduledTaskFactory scheduledTaskFactory;
    private final ExecutionPlanLogger executionPlanLogger;
    private final ScheduledExecutorService scheduler;

    private static final AtomicInteger timeElapsedInSeconds = new AtomicInteger(0);

    public ScheduledTaskManager(ScheduledTaskFactory scheduledTaskFactory,
                                ExecutionPlanLogger executionPlanLogger) {
        this.scheduledTaskFactory = scheduledTaskFactory;
        this.executionPlanLogger = executionPlanLogger;
        this.scheduler = Executors.newScheduledThreadPool(CORE_THREAD_POOL_SIZE);
    }

    /**
     * Executes all registered tasks by scheduling them using a ScheduledExecutorService.
     */
    public void execute() {

        this.scheduledTaskFactory.createAllTasks().forEach(scheduledTask -> {
            scheduleTask(scheduler, scheduledTask);
        });
    }

    private void scheduleTask(ScheduledExecutorService scheduler, ScheduledTask scheduledTask) {
        scheduler.scheduleAtFixedRate(() -> {
            scheduledTask.run();

            executionPlanLogger.addTask(timeElapsedInSeconds.get(), scheduledTask.getName());

            if (scheduledTask instanceof ScheduledTaskA) {
                timeElapsedInSeconds.incrementAndGet();
            }
        }, scheduledTask.getInitialDelayInSeconds(), scheduledTask.getIntervalInSeconds(), TimeUnit.SECONDS);
    }

    /**
     * Stops the scheduler and releases associated resources.
     */
    public void stop() {
        // Shut down the scheduler to release resources.
        // You can call this method when the task manager is no longer needed.
        scheduler.shutdown();
    }
}
