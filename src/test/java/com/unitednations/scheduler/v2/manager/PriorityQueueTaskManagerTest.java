package com.unitednations.scheduler.v2.manager;

import com.unitednations.scheduler.service.ExecutionPlanLogger;
import com.unitednations.scheduler.v2.tasks.Task;
import com.unitednations.scheduler.v2.tasks.TaskA;
import com.unitednations.scheduler.v2.tasks.TaskB;
import com.unitednations.scheduler.v2.tasks.TaskFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.*;

class PriorityQueueTaskManagerTest {

    private TaskFactory taskFactoryMock;
    private ExecutionPlanLogger executionPlanLoggerMock;

    @BeforeEach
    void setUp() {
        taskFactoryMock = Mockito.mock(TaskFactory.class);
        executionPlanLoggerMock = Mockito.mock(ExecutionPlanLogger.class);
    }

    @Test
    void testExecute_TaskAAndTaskB_ExecutedCorrectly() {
        // Create TaskA and TaskB instances with next execution times as 1 and 2 seconds respectively.
        Task taskA = new TaskA(1, 1);
        Task taskB = new TaskB(2, 5);

        // Return TaskA and TaskB from the task factory when createAllTasks() is called.
        when(taskFactoryMock.createAllTasks()).thenReturn(List.of(taskA, taskB));

        // Create the task manager with the mocked task factory and execution plan logger.
        PriorityQueueTaskManager taskManager = new PriorityQueueTaskManager(taskFactoryMock, executionPlanLoggerMock);

        // Execute the task manager.
        taskManager.execute();

        // Wait for the tasks to be executed (considering the EXECUTION_INTERVAL_IN_MS = 1000 milliseconds).
        try {
            Thread.sleep(3800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that TaskA and TaskB are executed at the correct times.
        verify(executionPlanLoggerMock).addTask(1L, "A");
        verify(executionPlanLoggerMock).addTask(2L, "A");
        verify(executionPlanLoggerMock).addTask(3L, "A");
        verify(executionPlanLoggerMock).addTask(4L, "A");
    }

    @Test
    void testExecute_TaskAAndTaskB_IntervalRespected() {
        // Create TaskA and TaskB instances with next execution times as 1 and 2 seconds respectively.
        Task taskA = new TaskA(1, 1);
        Task taskB = new TaskB(2, 5);

        // Return TaskA and TaskB from the task factory when createAllTasks() is called.
        when(taskFactoryMock.createAllTasks()).thenReturn(List.of(taskA, taskB));

        // Create the task manager with the mocked task factory and execution plan logger.
        PriorityQueueTaskManager taskManager = new PriorityQueueTaskManager(taskFactoryMock, executionPlanLoggerMock);

        // Execute the task manager.
        taskManager.execute();

        // Wait for the tasks to be executed (considering the EXECUTION_INTERVAL_IN_MS = 1000 milliseconds).
        try {
            Thread.sleep(5800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that TaskA and TaskB are executed at the correct intervals.
        verify(executionPlanLoggerMock).addTask(1L, "A");
        verify(executionPlanLoggerMock).addTask(2L, "A");
        verify(executionPlanLoggerMock).addTask(3L, "A");
        verify(executionPlanLoggerMock).addTask(4L, "A");
        verify(executionPlanLoggerMock).addTask(5L, "A");
        verify(executionPlanLoggerMock).addTask(6L, "A");
    }
}
