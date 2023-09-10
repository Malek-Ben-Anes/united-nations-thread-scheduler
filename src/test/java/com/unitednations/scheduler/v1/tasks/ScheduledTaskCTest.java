package com.unitednations.scheduler.v1.tasks;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.Semaphore;

class ScheduledTaskCTest {

    @Test
    void testScheduledTaskCExecution() throws InterruptedException {
        // Create a mock of the Semaphore
        Semaphore semaphoreMock = Mockito.mock(Semaphore.class);

        // Create a new ScheduledTaskA instance
        ScheduledTaskC taskC = new ScheduledTaskC( 0, 1);
        ScheduledTask.semaphore = semaphoreMock;

        // Call the run method on the task
        taskC.run();

        // Verify that the semaphore's acquire and release methods are called once
        Mockito.verify(semaphoreMock, Mockito.times(1)).acquire();
        Mockito.verify(semaphoreMock, Mockito.times(1)).release();
    }

}
