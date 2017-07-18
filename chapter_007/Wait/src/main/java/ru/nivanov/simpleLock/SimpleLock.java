package ru.nivanov.simpleLock;

/**
 * Created by Nikolay Ivanov on 18.07.2017.
 */
class SimpleLock {

    private volatile boolean isLocked = false;
    private Thread exclusiveLockOwner;

    /**
     * Lock method.
     */
    public synchronized void lock() {
        while (isLocked) {
            try {
                wait();
                System.out.println(String.format("%s is waiting", Thread.currentThread().getName()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        isLocked = true;
        exclusiveLockOwner = Thread.currentThread();
    }

    /**
     * Unlock method.
     */
    public synchronized void unlock() {

        if (Thread.currentThread() == exclusiveLockOwner && isLocked) {
            isLocked = false;
            exclusiveLockOwner = null;
            notifyAll();
        } else throw new UnsupportedOperationException();

    }


}
