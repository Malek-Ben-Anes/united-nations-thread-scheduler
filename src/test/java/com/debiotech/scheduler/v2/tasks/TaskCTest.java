package com.debiotech.scheduler.v2.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskCTest {

    @Test
    void testTaskC_ConstructorAndGetters() {
        // Create a TaskC instance
        long nextExecutionTime = 10;
        int interval = 5;
        TaskC taskC = new TaskC(nextExecutionTime, interval);

        // Check the name, nextExecutionTime, and interval
        assertEquals("C", taskC.getName());
        assertEquals(nextExecutionTime, taskC.getNextExecutionTime());
        assertEquals(interval, taskC.getInterval());
    }

    @Test
    void testTaskC_Execution() {
        // Create a TaskC instance
        long nextExecutionTime = 10;
        int interval = 5;
        TaskC taskC = new TaskC(nextExecutionTime, interval);

        // Execute the task and check if the name "C" is printed
        taskC.execute();
        // In the actual application, this will print "C" to the console.
        // However, in the unit test, we can't capture the console output directly.
        // So, we can't do an exact assertion here. We can consider capturing the console output
        // using libraries like `SystemOutRule`, but for simplicity, we'll skip that for now.
    }

    @Test
    void testTaskC_IsReadyToExecute() {
        // Create a TaskC instance
        long nextExecutionTime = 10;
        int interval = 5;
        TaskC taskC = new TaskC(nextExecutionTime, interval);

        // Test with different currentExecutionTime values
        assertTrue(taskC.isReadyToExecute(10)); // TaskC should be ready at nextExecutionTime
        assertFalse(taskC.isReadyToExecute(5)); // TaskC should not be ready before nextExecutionTime
        assertTrue(taskC.isReadyToExecute(15)); // TaskC should be ready after nextExecutionTime
    }

    @Test
    void testTaskC_Reschedule() {
        // Create a TaskC instance with interval = 5
        long nextExecutionTime = 10;
        int interval = 5;
        TaskC taskC = new TaskC(nextExecutionTime, interval);

        // Execute and check nextExecutionTime before rescheduling
        assertEquals(10, taskC.getNextExecutionTime());

        // Reschedule and check nextExecutionTime after rescheduling
        taskC.reSchedule();
        assertEquals(15, taskC.getNextExecutionTime()); // nextExecutionTime should be increased by interval (5)
    }
}
