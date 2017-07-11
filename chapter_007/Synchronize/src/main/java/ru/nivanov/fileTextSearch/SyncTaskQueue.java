package ru.nivanov.fileTextSearch;

/**
 * Created by Nikolay Ivanov on 04.07.2017.
 * @param <E> ..
 */
class SyncTaskQueue<E> {

    private final Object[] items;
    private volatile int count;
    private int putIndex;
    private int takeIndex;

    /**
     * Constructor ..
     * @param capacity ..
     */
    SyncTaskQueue(int capacity) {
        this.items = new Object[capacity];
    }

    /**
     * Throws NullPointerException if argument is null.
     * @param v the element
     */
    private static void checkNotNull(Object v) {
        if (v == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Inserts the specified element at the tail of this queue, waiting
     * for space to become available if the queue is full.
     * @param obj ..
     */
    synchronized void put(E obj) {
        checkNotNull(obj);
        while (count == this.items.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.items[putIndex] = obj;
        if (++putIndex == this.items.length) {
            putIndex = 0;
        }
        count++;
        notifyAll();
    }

    /**
     * Extracts element at current take position, advances, and signals.
     * @return value.
     */
    synchronized E take() {
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        E obj = (E) this.items[takeIndex];
        this.items[takeIndex] = null;
        if (++takeIndex == this.items.length) {
            takeIndex = 0;
        }
        count--;
        notifyAll();
        return obj;
    }
}
