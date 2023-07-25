package com.debiotech.scheduler.v2.tasks;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskFactoryTest {

    @Test
    void testCreateTaskA() {
        TaskFactory taskFactory = new TaskFactory();
        Task taskA = taskFactory.createTaskA();

        assertEquals("A", taskA.getName());
        assertEquals(1, taskA.getNextExecutionTime());
        assertEquals(1, taskA.getInterval());
        assertTrue(taskA instanceof TaskA);
    }

    @Test
    void testCreateTaskB() {
        TaskFactory taskFactory = new TaskFactory();
        Task taskB = taskFactory.createTaskB();

        assertEquals("B", taskB.getName());
        assertEquals(2, taskB.getNextExecutionTime());
        assertEquals(5, taskB.getInterval());
        assertTrue(taskB instanceof TaskB);
    }

    @Test
    void testCreateTaskC() {
        TaskFactory taskFactory = new TaskFactory();
        Task taskC = taskFactory.createTaskC();

        assertEquals("C", taskC.getName());
        assertEquals(3, taskC.getNextExecutionTime());
        assertEquals(5, taskC.getInterval());
        assertTrue(taskC instanceof TaskC);
    }

    @Test
    void testCreateTaskD() {
        TaskFactory taskFactory = new TaskFactory();
        Task taskD = taskFactory.createTaskD();

        assertEquals("D", taskD.getName());
        assertEquals(4, taskD.getNextExecutionTime());
        assertEquals(10, taskD.getInterval());
        assertTrue(taskD instanceof TaskD);
    }

    @Test
    void testCreateTaskE() {
        TaskFactory taskFactory = new TaskFactory();
        Task taskE = taskFactory.createTaskE();

        assertEquals("E", taskE.getName());
        assertEquals(5, taskE.getNextExecutionTime());
        assertEquals(10, taskE.getInterval());
        assertTrue(taskE instanceof TaskE);
    }

    @Test
    void testCreateAllTasks() {
        TaskFactory taskFactory = new TaskFactory();
        List<Task> allTasks = taskFactory.createAllTasks();

        assertEquals(5, allTasks.size());

        // Check the order and types of tasks in the list
        assertTrue(allTasks.get(0) instanceof TaskA);
        assertTrue(allTasks.get(1) instanceof TaskB);
        assertTrue(allTasks.get(2) instanceof TaskC);
        assertTrue(allTasks.get(3) instanceof TaskD);
        assertTrue(allTasks.get(4) instanceof TaskE);
    }
}
