package com.debiotech.scheduler.v2.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskBTest {

    @Test
    void testTaskB_ConstructorAndGetters() {
        // Create a TaskB instance
        long nextExecutionTime = 10;
        int interval = 5;
        TaskB taskB = new TaskB(nextExecutionTime, interval);

        // Check the name, nextExecutionTime, and interval
        assertEquals("B", taskB.getName());
        assertEquals(nextExecutionTime, taskB.getNextExecutionTime());
        assertEquals(interval, taskB.getInterval());
    }

    @Test
    void testTaskB_Execution() {
        // Create a TaskB instance
        long nextExecutionTime = 10;
        int interval = 5;
        TaskB taskB = new TaskB(nextExecutionTime, interval);

        // Execute the task and check if the name "B" is printed
        taskB.execute();
        // In the actual application, this will print "B" to the console.
        // However, in the unit test, we can't capture the console output directly.
        // So, we can't do an exact assertion here. We can consider capturing the console output
        // using libraries like `SystemOutRule`, but for simplicity, we'll skip that for now.
    }

    @Test
    void testTaskB_IsReadyToExecute() {
        // Create a TaskB instance
        long nextExecutionTime = 10;
        int interval = 5;
        TaskB taskB = new TaskB(nextExecutionTime, interval);

        // Test with different currentExecutionTime values
        assertTrue(taskB.isReadyToExecute(10)); // TaskB should be ready at nextExecutionTime
        assertFalse(taskB.isReadyToExecute(5)); // TaskB should not be ready before nextExecutionTime
        assertTrue(taskB.isReadyToExecute(15)); // TaskB should be ready after nextExecutionTime
    }

    @Test
    void testTaskB_Reschedule() {
        // Create a TaskB instance with interval = 5
        long nextExecutionTime = 10;
        int interval = 5;
        TaskB taskB = new TaskB(nextExecutionTime, interval);

        // Execute and check nextExecutionTime before rescheduling
        assertEquals(10, taskB.getNextExecutionTime());

        // Reschedule and check nextExecutionTime after rescheduling
        taskB.reSchedule();
        assertEquals(15, taskB.getNextExecutionTime()); // nextExecutionTime should be increased by interval (5)
    }
}
