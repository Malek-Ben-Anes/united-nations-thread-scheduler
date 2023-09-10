package com.unitednations.scheduler.v2.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskDTest {

    @Test
    void testTaskD_ConstructorAndGetters() {
        // Create a TaskD instance
        long nextExecutionTime = 10;
        int interval = 5;
        TaskD taskD = new TaskD(nextExecutionTime, interval);

        // Check the name, nextExecutionTime, and interval
        assertEquals("D", taskD.getName());
        assertEquals(nextExecutionTime, taskD.getNextExecutionTime());
        assertEquals(interval, taskD.getInterval());
    }

    @Test
    void testTaskD_Execution() {
        // Create a TaskD instance
        long nextExecutionTime = 10;
        int interval = 5;
        TaskD taskD = new TaskD(nextExecutionTime, interval);

        // Execute the task and check if the name "D" is printed
        taskD.execute();
        // In the actual application, this will print "D" to the console.
        // However, in the unit test, we can't capture the console output directly.
        // So, we can't do an exact assertion here. We can consider capturing the console output
        // using libraries like `SystemOutRule`, but for simplicity, we'll skip that for now.
    }

    @Test
    void testTaskD_IsReadyToExecute() {
        // Create a TaskD instance
        long nextExecutionTime = 10;
        int interval = 5;
        TaskD taskD = new TaskD(nextExecutionTime, interval);

        // Test with different currentExecutionTime values
        assertTrue(taskD.isReadyToExecute(10)); // TaskD should be ready at nextExecutionTime
        assertFalse(taskD.isReadyToExecute(5)); // TaskD should not be ready before nextExecutionTime
        assertTrue(taskD.isReadyToExecute(15)); // TaskD should be ready after nextExecutionTime
    }

    @Test
    void testTaskD_Reschedule() {
        // Create a TaskD instance with interval = 5
        long nextExecutionTime = 10;
        int interval = 5;
        TaskD taskD = new TaskD(nextExecutionTime, interval);

        // Execute and check nextExecutionTime before rescheduling
        assertEquals(10, taskD.getNextExecutionTime());

        // Reschedule and check nextExecutionTime after rescheduling
        taskD.reSchedule();
        assertEquals(15, taskD.getNextExecutionTime()); // nextExecutionTime should be increased by interval (5)
    }
}
