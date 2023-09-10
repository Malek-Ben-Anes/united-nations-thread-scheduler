package com.unitednations.scheduler.v1.manager;

import com.unitednations.scheduler.service.ExecutionPlanLogger;
import com.unitednations.scheduler.v1.tasks.ScheduledTask;
import com.unitednations.scheduler.v1.tasks.ScheduledTaskFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ScheduledTaskManagerTest {

    private ScheduledTaskFactory taskFactoryMock;
    private ExecutionPlanLogger executionPlanLoggerMock;
    private ScheduledTaskManager taskManager;
    private ScheduledExecutorService schedulerMock;

    @BeforeEach
    void setUp() {
        // Create mock instances of the dependencies
        taskFactoryMock = Mockito.mock(ScheduledTaskFactory.class);
        executionPlanLoggerMock = Mockito.mock(ExecutionPlanLogger.class);
        schedulerMock = Mockito.mock(ScheduledExecutorService.class);

        // Create the task manager with the mock dependencies
        taskManager = new ScheduledTaskManager(taskFactoryMock, executionPlanLoggerMock, schedulerMock);
    }

    @Test
    void testExecute() {
        // Create some mock tasks
        ScheduledTask task1Mock = Mockito.mock(ScheduledTask.class);
        ScheduledTask task2Mock = Mockito.mock(ScheduledTask.class);
        List<ScheduledTask> mockTasks = List.of(task1Mock, task2Mock);

        // Set up the mock task factory to return the mock tasks
        when(taskFactoryMock.createAllTasks()).thenReturn(mockTasks);

        // Call the execute method of the task manager
        taskManager.execute();

        // Verify that the tasks are scheduled with the correct initial delays and intervals
        verify(schedulerMock, times(2)).scheduleAtFixedRate(any(), anyLong(), anyLong(), any());
    }

    @Test
    void testStop() {
        // Call the stop method of the task manager
        taskManager.stop();

        // Verify that the scheduler's shutdown method is called
        verify(schedulerMock).shutdown();
    }
}
