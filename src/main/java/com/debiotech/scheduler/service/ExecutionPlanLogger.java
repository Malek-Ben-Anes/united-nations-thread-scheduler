package com.debiotech.scheduler.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * A class to log and output the execution plan of tasks per second.
 */
public class ExecutionPlanLogger {

    private final Map<Long, List<String>> executedTasksPerSecond = new ConcurrentHashMap<>();

    /**
     * Adds a task to the execution plan log for a given time elapsed (in seconds).
     *
     * @param startRunningTimeInSeconds The time elapsed in seconds.
     * @param taskName                  The taskName to be added to the log.
     */
    public synchronized void addTask(long startRunningTimeInSeconds, String taskName) {
        List<String> tasks = executedTasksPerSecond.getOrDefault(startRunningTimeInSeconds, new ArrayList<>());
        tasks.add(taskName);
        tasks = tasks.stream().sorted().collect(Collectors.toList());
        executedTasksPerSecond.put(startRunningTimeInSeconds, tasks);
    }

    /**
     * Adds a task to the execution plan log for a given time elapsed (in seconds).
     * Then it logs all executed tasks every 5 seconds
     *
     * @param startRunningTimeInSeconds The time elapsed in seconds.
     * @param taskName                  The taskName to be added to the log.
     */
    public synchronized void addTaskAndLog(long startRunningTimeInSeconds, String taskName) {
        addTask(startRunningTimeInSeconds, taskName);

        // We choose to print the execution log every 5 seconds.
        // This statement should be removed.
        if (startRunningTimeInSeconds > 0 && startRunningTimeInSeconds % 5 == 0) {
            System.out.println("\n====        LOGGING FROM EXECUTION PLANNER       ===");
            outputToConsole();
            System.out.println("Total running seconds: " + executedTasksPerSecond.size() + "s\n");
        }
    }

    /**
     * Prints the execution plan log to the console.
     */
    public synchronized void outputToConsole() {
        for (Map.Entry<Long, List<String>> entry : executedTasksPerSecond.entrySet()) {
            String formattedOutput = String.format("%d -> %s", entry.getKey(), entry.getValue());
            System.out.println(formattedOutput);
        }
    }

    /**
     * Outputs the execution plan log to a file with the given file name.
     *
     * @param fileName The name of the file to write the execution plan log.
     */
    public synchronized void outputToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<Long, List<String>> entry : executedTasksPerSecond.entrySet()) {
                String formattedOutput = String.format("%d -> %s", entry.getKey(), entry.getValue());
                writer.write(formattedOutput);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the IOException if needed.
        }
    }

    /**
     * Gets an unmodifiable view of the execution plan log.
     *
     * @return The execution plan log as an unmodifiable map.
     */
    public synchronized Map<Long, List<String>> getExecutionPlanLog() {
        return Collections.unmodifiableMap(executedTasksPerSecond);
    }
}
