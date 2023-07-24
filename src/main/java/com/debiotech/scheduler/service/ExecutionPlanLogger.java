package com.debiotech.scheduler.service;

import com.debiotech.scheduler.tasks.ScheduledTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExecutionPlanLogger {

    private static  final Map<Integer, List<String>> executedTasksPerSecond = new ConcurrentHashMap<>();

    public void addTask(int timeElapsed, ScheduledTask task) {
        List<String> tasks = executedTasksPerSecond.getOrDefault(timeElapsed, new ArrayList<>());
        tasks.add(task.getName());
        executedTasksPerSecond.put(timeElapsed, tasks);

        if (timeElapsed % 20 == 0) {
            printPlan();
        }
    }

    public void printPlan() {
        for (Map.Entry<Integer, List<String>> e : executedTasksPerSecond.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }
}
