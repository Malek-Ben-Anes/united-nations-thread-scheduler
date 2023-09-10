package com.unitednations.scheduler.v2.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testCompareTo_SameNextExecutionTime() {
        // Create two tasks with the same nextExecutionTime
        Task task1 = new Task("Task1", 10, 5) {};
        Task task2 = new Task("Task2", 10, 3) {};

        // Task names should be used for comparison since nextExecutionTime is the same
        assertTrue(task1.compareTo(task2) < 0);
        assertTrue(task2.compareTo(task1) > 0);
    }

    @Test
    void testCompareTo_DifferentNextExecutionTime() {
        // Create two tasks with different nextExecutionTime
        Task task1 = new Task("Task1", 10, 5) {};
        Task task2 = new Task("Task2", 15, 3) {};

        // Tasks should be compared based on their nextExecutionTime
        assertTrue(task1.compareTo(task2) < 0);
        assertTrue(task2.compareTo(task1) > 0);
    }

    @Test
    void testCompareTo_SameTask() {
        // Create two identical tasks
        Task task1 = new Task("Task", 15, 5) {};
        Task task2 = new Task("Task", 15, 5) {};

        // Since the tasks are the same, the comparison result should be 0
        assertEquals(0, task1.compareTo(task2));
    }
}