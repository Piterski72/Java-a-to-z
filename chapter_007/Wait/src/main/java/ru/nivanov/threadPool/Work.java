package ru.nivanov.threadPool;

/**
 * Created by Nikolay Ivanov on 13.07.2017.
 */
class Work implements Runnable {

    private final String name;

    /**
     * Constructor.
     * @param name ..
     */
    Work(String name) {
        this.name = name;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println(String.format("thread %s completed work %s", Thread.currentThread().getName(), this.name));
    }
}
