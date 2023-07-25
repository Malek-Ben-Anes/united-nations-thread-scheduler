package com.debiotech.scheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.mockito.Mockito.*;

class DebiotechSchedulerApplicationTest {

    private DebiotechSchedulerApplication debiotechSchedulerApplication;

    @BeforeEach
    void setUp() {
        debiotechSchedulerApplication = new DebiotechSchedulerApplication();
    }

    @Test
    void testMainWithExit() {
        // Mock user input to simulate entering "0" for exiting the program
        InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());
        System.setIn(inputStream);

        // Run the main method
        DebiotechSchedulerApplication.main(new String[]{});

        // No need to assert anything since we just want to check if the program terminates without exceptions
    }

    @Test
    void testMainWithInvalidChoiceThenDoNothing() {
        // Mock user input to simulate entering "3" (invalid choice) and then "1" (scheduled task algorithm)
        InputStream inputStream = new ByteArrayInputStream("3\n1\n0\n".getBytes());
        System.setIn(inputStream);

        // Mock the executeScheduledTaskAlgorithm and launchPriorityQueueAlgorithm methods
        DebiotechSchedulerApplication mockApplication = Mockito.spy(debiotechSchedulerApplication);
        doNothing().when(mockApplication).executeScheduledTaskAlgorithm();
        doNothing().when(mockApplication).launchPriorityQueueAlgorithm();

        // Run the main method
        DebiotechSchedulerApplication.main(new String[]{});
    }
}
