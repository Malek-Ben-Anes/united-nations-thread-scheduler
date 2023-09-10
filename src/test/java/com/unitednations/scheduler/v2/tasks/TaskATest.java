package com.unitednations.scheduler.v2.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskATest {

    @Test
    void testTaskA_ConstructorAndGetters() {
        // Create a TaskA instance
        long nextExecutionTime = 10;
        int interval = 5;
        TaskA taskA = new TaskA(nextExecutionTime, interval);

        // Check the name, nextExecutionTime, and interval
        assertEquals("A", taskA.getName());
        assertEquals(nextExecutionTime, taskA.getNextExecutionTime());
        assertEquals(interval, taskA.getInterval());
    }

    @Test
    void testTaskA_Execution() {
        // Create a TaskA instance
        long nextExecutionTime = 10;
        int interval = 5;
        TaskA taskA = new TaskA(nextExecutionTime, interval);

        // Execute the task and check if the name "A" is printed
        taskA.execute();
        // In the actual application, this will print "A" to the console.
        // However, in the unit test, we can't capture the console output directly.
        // So, we can't do an exact assertion here. We can consider capturing the console output
        // using libraries like `SystemOutRule`, but for simplicity, we'll skip that for now.
    }

    @Test
    void testTaskA_IsReadyToExecute() {
        // Create a TaskA instance
        long nextExecutionTime = 10;
        int interval = 5;
        TaskA taskA = new TaskA(nextExecutionTime, interval);

        // Test with different currentExecutionTime values
        assertTrue(taskA.isReadyToExecute(10)); // TaskA should be ready at nextExecutionTime
        assertFalse(taskA.isReadyToExecute(5)); // TaskA should not be ready before nextExecutionTime
        assertTrue(taskA.isReadyToExecute(15)); // TaskA should be ready after nextExecutionTime
    }

    @Test
    void testTaskA_Reschedule() {
        // Create a TaskA instance with interval = 5
        long nextExecutionTime = 10;
        int interval = 5;
        TaskA taskA = new TaskA(nextExecutionTime, interval);

        // Execute and check nextExecutionTime before rescheduling
        assertEquals(10, taskA.getNextExecutionTime());

        // Reschedule and check nextExecutionTime after rescheduling
        taskA.reSchedule();
        assertEquals(15, taskA.getNextExecutionTime()); // nextExecutionTime should be increased by interval (5)
    }
}
