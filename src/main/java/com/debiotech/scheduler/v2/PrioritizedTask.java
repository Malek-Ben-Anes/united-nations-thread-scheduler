package com.debiotech.scheduler.v2;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Inspired by example from javadoc {@link java.util.concurrent.PriorityBlockingQueue}
 */
public class PrioritizedTask implements Comparable<PrioritizedTask>, Runnable {
    /**
     * Needed for support sequence number across all instances.
     * Long type will be enough for along time.
     * Needed for first-in-first-out logic support.
     */
    private static final AtomicLong seq = new AtomicLong(0);
    /**
     * Sequence number of this instance.
     * Needed for first-in-first-out logic support.
     */
    private final long seqNum;

    private final Runnable command;
    private final Integer priority;

    public PrioritizedTask(Runnable command, int priority) {
        seqNum = seq.getAndIncrement();
        this.command = command;
        this.priority = priority;
    }

    @Override
    public void run() {
        command.run();
    }

    /**
     * Implements first-in-first-out logic.
     */
    @Override
    public int compareTo(PrioritizedTask other) {
        int res = priority.compareTo(other.priority);
        if (res == 0 && other != this) {
            res = (seqNum < other.seqNum ? -1 : 1);
        }
        return res;
    }

    @Override
    public String toString() {
        return "{" + seqNum + ", " + priority + "}";
    }
}
