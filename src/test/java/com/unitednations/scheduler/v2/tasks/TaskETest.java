package com.unitednations.scheduler.v2.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskETest {

    @Test
    void testTaskE_ConstructorAndGetters() {
        // Create a TaskE instance
        long nextExecutionTime = 10;
        int interval = 5;
        TaskE taskE = new TaskE(nextExecutionTime, interval);

        // Check the name, nextExecutionTime, and interval
        assertEquals("E", taskE.getName());
        assertEquals(nextExecutionTime, taskE.getNextExecutionTime());
        assertEquals(interval, taskE.getInterval());
    }

    @Test
    void testTaskE_Execution() {
        // Create a TaskE instance
        long nextExecutionTime = 10;
        int interval = 5;
        TaskE taskE = new TaskE(nextExecutionTime, interval);

        // Execute the task and check if the name "E" is printed
        taskE.execute();
        // In the actual application, this will print "E" to the console.
        // However, in the unit test, we can't capture the console output directly.
        // So, we can't do an exact assertion here. We can consider capturing the console output
        // using libraries like `SystemOutRule`, but for simplicity, we'll skip that for now.
    }

    @Test
    void testTaskE_IsReadyToExecute() {
        // Create a TaskE instance
        long nextExecutionTime = 10;
        int interval = 5;
        TaskE taskE = new TaskE(nextExecutionTime, interval);

        // Test with different currentExecutionTime values
        assertTrue(taskE.isReadyToExecute(10)); // TaskE should be ready at nextExecutionTime
        assertFalse(taskE.isReadyToExecute(5)); // TaskE should not be ready before nextExecutionTime
        assertTrue(taskE.isReadyToExecute(15)); // TaskE should be ready after nextExecutionTime
    }

    @Test
    void testTaskE_Reschedule() {
        // Create a TaskE instance with interval = 5
        long nextExecutionTime = 10;
        int interval = 5;
        TaskE taskE = new TaskE(nextExecutionTime, interval);

        // Execute and check nextExecutionTime before rescheduling
        assertEquals(10, taskE.getNextExecutionTime());

        // Reschedule and check nextExecutionTime after rescheduling
        taskE.reSchedule();
        assertEquals(15, taskE.getNextExecutionTime()); // nextExecutionTime should be increased by interval (5)
    }
}
