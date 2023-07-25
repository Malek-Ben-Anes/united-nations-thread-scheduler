package com.debiotech.scheduler.v1.tasks;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ScheduledTaskTest {

    private ScheduledTask scheduledTask;
    private AtomicInteger executionCount;
    private Semaphore semaphoreMock;

    @BeforeEach
    public void setUp() {
        executionCount = new AtomicInteger(0);
        semaphoreMock = mock(Semaphore.class);

        // Create a new instance of ScheduledTask with a simple Runnable logic
        scheduledTask = new ScheduledTask("TestTask", () -> executionCount.incrementAndGet(), 0, 1) {
        };
        ScheduledTask.semaphore = semaphoreMock;
    }

    @Test
    public void testRunTaskWithSemaphoreAcquired() throws InterruptedException {
        // Arrange
        when(semaphoreMock.tryAcquire()).thenReturn(true);

        // Act
        scheduledTask.run();

        // Assert
        assertEquals(1, executionCount.get());
        verify(semaphoreMock, times(1)).acquire();
        verify(semaphoreMock, times(1)).release();
    }

    @Test
    public void testRunTaskWithSemaphoreThrowsException() throws InterruptedException {
        // Arrange
        doThrow(new InterruptedException("Error occurred")).when(semaphoreMock).acquire();

        // Act
        try {
            scheduledTask.run();
        } catch (Exception e) {
            e.printStackTrace();
            // do not propagate this exception
        }

        // Assert
        assertEquals(0, executionCount.get());
        verify(semaphoreMock, times(1)).acquire();
        verify(semaphoreMock, times(1)).release();
    }

    @Test
    public void testRunTaskWithSemaphoreIssueThrowsException() throws InterruptedException {
        // Arrange
        doThrow(new InterruptedException("Error occurred")).when(semaphoreMock).acquire();

        // Act and Assert
        assertThrows(RuntimeException.class, () -> scheduledTask.run());
    }
}
