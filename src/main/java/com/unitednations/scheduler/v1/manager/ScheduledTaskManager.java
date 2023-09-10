package com.unitednations.scheduler.v1.manager;

import com.unitednations.scheduler.service.ExecutionPlanLogger;
import com.unitednations.scheduler.v1.tasks.ScheduledTask;
import com.unitednations.scheduler.v1.tasks.ScheduledTaskFactory;
import com.unitednations.scheduler.v1.tasks.ScheduledTaskA;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A manager that schedules and executes the registered tasks using a ScheduledExecutorService.
 */
public class ScheduledTaskManager {

    // Only allow two tasks at most to run at a time.
    public static final int MAX_CONCURRENT_TASKS = 2;

    private final ScheduledTaskFactory scheduledTaskFactory;
    private final ExecutionPlanLogger executionPlanLogger;
    private final ScheduledExecutorService scheduledExecutorService;

    private static final AtomicInteger timeElapsedInSeconds = new AtomicInteger(0);

    public ScheduledTaskManager(ScheduledTaskFactory scheduledTaskFactory,
                                ExecutionPlanLogger executionPlanLogger,
                                ScheduledExecutorService scheduledExecutorService) {
        this.scheduledTaskFactory = scheduledTaskFactory;
        this.executionPlanLogger = executionPlanLogger;
        this.scheduledExecutorService = scheduledExecutorService;
    }

    /**
     * Executes all registered tasks by scheduling them using a ScheduledExecutorService.
     */
    public void execute() {
        System.out.println("Time (sec) -> Tasks");
        this.scheduledTaskFactory.createAllTasks().forEach(scheduledTask -> scheduleTask(scheduledExecutorService, scheduledTask));
    }

    public void scheduleTask(ScheduledExecutorService scheduler, ScheduledTask scheduledTask) {
        scheduler.scheduleAtFixedRate(() -> {

            scheduledTask.run();

            executionPlanLogger.addTaskAndLog(timeElapsedInSeconds.get(), scheduledTask.getName());

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
        scheduledExecutorService.shutdown();
    }
}
