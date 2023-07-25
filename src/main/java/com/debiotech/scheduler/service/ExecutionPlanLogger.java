package com.debiotech.scheduler.service;

import com.debiotech.scheduler.v1.tasks.ScheduledTask;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A class to log and output the execution plan of tasks per second.
 */
public class ExecutionPlanLogger {

    private static final Map<Integer, List<String>> executedTasksPerSecond = new ConcurrentHashMap<>();

    /**
     * Adds a task to the execution plan log for a given time elapsed (in seconds).
     *
     * @param timeElapsed The time elapsed in seconds.
     * @param task        The ScheduledTask to be added to the log.
     */
    public synchronized void addTask(int timeElapsed, ScheduledTask task) {
        List<String> tasks = executedTasksPerSecond.getOrDefault(timeElapsed, new ArrayList<>());
        tasks.add(task.getName());
        executedTasksPerSecond.put(timeElapsed, tasks);

        if (timeElapsed % 20 == 0) {
            outputToConsole();
        }
    }

    /**
     * Prints the execution plan log to the console.
     */
    public synchronized void outputToConsole() {
        for (Map.Entry<Integer, List<String>> entry : executedTasksPerSecond.entrySet()) {
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
            for (Map.Entry<Integer, List<String>> entry : executedTasksPerSecond.entrySet()) {
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
    public synchronized Map<Integer, List<String>> getExecutionPlanLog() {
        return Collections.unmodifiableMap(executedTasksPerSecond);
    }
}