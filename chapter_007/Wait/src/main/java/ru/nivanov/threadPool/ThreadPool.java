package ru.nivanov.threadPool;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Nikolay Ivanov on 12.07.2017.
 */
public class ThreadPool {

    private static final int CPU_NUMBER = Runtime.getRuntime().availableProcessors();
    private final Queue<Work> queue;
    private volatile boolean isRunning = true;

    /**
     * Constructor.
     */
    private ThreadPool() {

        this.queue = new LinkedList<>();
        for (int i = 0; i < CPU_NUMBER; i++) {
            new Thread(new Worker()).start();
        }
    }

    /**
     * Main methid.
     * @param args ..
     */
    public static void main(String[] args) {
        final int hundred = 100;

        ThreadPool threadPool = new ThreadPool();

        Thread executor = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < hundred; i++) {
                    threadPool.add(new Work(String.format("task# %s", String.valueOf(i))));
                }
                threadPool.shutDown();
            }
        });

        executor.start();
        try {
            executor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Adds work to thread pool.
     * @param work ..
     */
    public void add(Work work) {

        synchronized (this.queue) {
            this.queue.add(work);
        }
    }

    /**
     * Shuts down pool.
     */
    private void shutDown() {
        isRunning = false;
    }

    /**
     * Inner class Worker.
     */
    private class Worker implements Runnable {
        /**
         * Method for executing tasks.
         */
        public void run() {
            while (isRunning) {
                synchronized (queue) {
                    Runnable work = queue.poll();
                    if (work != null) {
                        work.run();
                    }
                }
            }
        }
    }

}
