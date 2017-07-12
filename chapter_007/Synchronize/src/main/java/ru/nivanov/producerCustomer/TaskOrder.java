package ru.nivanov.producerCustomer;

/**
 * Created by Nikolay Ivanov on 12.07.2017.
 * @param <E> ..
 */
class TaskOrder<E> {

    private E content;
    private boolean available = false;

    /**
     * Get int method.
     * @return content
     */
    synchronized E get() {
        while (!this.available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.available = false;
        notifyAll();
        return this.content;
    }

    /**
     * Put int method.
     * @param item ..
     */
    synchronized void put(E item) {
        while (this.available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.content = item;
        this.available = true;
        notifyAll();
    }
}
