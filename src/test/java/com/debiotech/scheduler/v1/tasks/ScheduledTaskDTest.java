package com.debiotech.scheduler.v1.tasks;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.Semaphore;

class ScheduledTaskDTest {

    @Test
    void testScheduledTaskDExecution() throws InterruptedException {
        // Create a mock of the Semaphore
        Semaphore semaphoreMock = Mockito.mock(Semaphore.class);

        // Create a new ScheduledTaskA instance
        ScheduledTaskD taskD = new ScheduledTaskD(0, 1);
        ScheduledTask.semaphore = semaphoreMock;

        // Call the run method on the task
        taskD.run();

        // Verify that the semaphore's acquire and release methods are called once
        Mockito.verify(semaphoreMock, Mockito.times(1)).acquire();
        Mockito.verify(semaphoreMock, Mockito.times(1)).release();
    }

}
