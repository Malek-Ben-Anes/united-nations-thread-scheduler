package com.debiotech.scheduler.v1.tasks;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScheduledTaskFactoryTest {

    @Test
    void testCreateAllTasks() {
        // Create a new instance of the ScheduledTaskFactory
        ScheduledTaskFactory taskFactory = new ScheduledTaskFactory();

        // Set the mock Semaphore in the factory
        ScheduledTask taskA = taskFactory.createTaskA();
        ScheduledTask taskB = taskFactory.createTaskB();
        ScheduledTask taskC = taskFactory.createTaskC();
        ScheduledTask taskD = taskFactory.createTaskD();
        ScheduledTask taskE = taskFactory.createTaskE();

        // Verify that the tasks are created with the correct initial delays and intervals
        assertEquals(0, taskA.getInitialDelayInSeconds());
        assertEquals(1, taskA.getIntervalInSeconds());
        assertEquals(taskB.getSemaphore(), ((ScheduledTask) taskA).getSemaphore());

        assertEquals(1, taskB.getInitialDelayInSeconds());
        assertEquals(5, taskB.getIntervalInSeconds());
        assertEquals(taskA.getSemaphore(), ((ScheduledTask) taskB).getSemaphore());

        assertEquals(2, taskC.getInitialDelayInSeconds());
        assertEquals(5, taskC.getIntervalInSeconds());
        assertEquals(taskA.getSemaphore(), ((ScheduledTask) taskC).getSemaphore());

        assertEquals(3, taskD.getInitialDelayInSeconds());
        assertEquals(10, taskD.getIntervalInSeconds());
        assertEquals(taskA.getSemaphore(), ((ScheduledTask) taskD).getSemaphore());

        assertEquals(4, taskE.getInitialDelayInSeconds());
        assertEquals(10, taskE.getIntervalInSeconds());
        assertEquals(taskA.getSemaphore(), ((ScheduledTask) taskE).getSemaphore());

        // Verify that the createAllTasks() method returns the correct list of tasks
        List<ScheduledTask> allTasks = taskFactory.createAllTasks();
        assertEquals(5, allTasks.size());
        assertEquals(allTasks.get(0).getClass(), ScheduledTaskA.class);
        assertEquals(allTasks.get(1).getClass(), ScheduledTaskB.class);
        assertEquals(allTasks.get(2).getClass(), ScheduledTaskC.class);
        assertEquals(allTasks.get(3).getClass(), ScheduledTaskD.class);
        assertEquals(allTasks.get(4).getClass(), ScheduledTaskE.class);
    }

}
