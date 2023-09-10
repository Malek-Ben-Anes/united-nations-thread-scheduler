package com.unitednations.scheduler.service;

import com.unitednations.scheduler.v1.tasks.ScheduledTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ExecutionPlanLoggerTest {

    private ExecutionPlanLogger logger;

    @BeforeEach
    public void setUp() {
        // Create a new ExecutionPlanLogger instance before each test
        logger = new ExecutionPlanLogger();
    }

    @Test
    public void testAddingSingleTaskForSpecificSecond() {
        // Arrange
        ScheduledTask task = createMockTask("A");
        int timeElapsed = 1;

        // Act
        logger.addTask(timeElapsed, task.getName());

        // Assert
        String expected = "{1=[A]}";
        assertEquals(expected, logger.getExecutionPlanLog().toString());
    }

    @Test
    public void testAddingMultipleTasksForSameSecond() {
        // Arrange
        ScheduledTask taskA = createMockTask("A");
        ScheduledTask taskB = createMockTask("B");
        int timeElapsed = 1;

        // Act
        logger.addTask(timeElapsed, taskA.getName());
        logger.addTask(timeElapsed, taskB.getName());

        // Assert
        String expected = "{1=[A, B]}";
        assertEquals(expected, logger.getExecutionPlanLog().toString());
    }

    @Test
    public void testAddingTasksForDifferentSeconds() {
        // Arrange
        ScheduledTask taskA = createMockTask("A");
        ScheduledTask taskB = createMockTask("B");
        int timeElapsed1 = 1;
        int timeElapsed2 = 2;

        // Act
        logger.addTask(timeElapsed1, taskA.getName());
        logger.addTask(timeElapsed2, taskB.getName());

        // Assert
        String expected = "{1=[A], 2=[B]}";
        assertEquals(expected, logger.getExecutionPlanLog().toString());
    }

    @Test
    public void testAddingTasksForMultipleSeconds() {
        // Arrange
        ScheduledTask taskA = createMockTask("A");
        ScheduledTask taskB = createMockTask("B");
        int timeElapsed1 = 1;
        int timeElapsed2 = 2;

        // Act
        logger.addTask(timeElapsed1, taskA.getName());
        logger.addTask(timeElapsed1, taskB.getName());
        logger.addTask(timeElapsed2, taskA.getName());
        logger.addTask(timeElapsed2, taskB.getName());

        // Assert
        String expected = "{1=[A, B], 2=[A, B]}";
        assertEquals(expected, logger.getExecutionPlanLog().toString());
    }

    // Helper method to create a mock ScheduledTask
    private ScheduledTask createMockTask(String name) {
        return new ScheduledTask(name, () -> {}, 0, 1) {
            @Override
            public String getName() {
                return name;
            }
        };
    }
}
