package ru.nivanov.producerCustomer;

/**
 * Created by Nikolay Ivanov on 12.07.2017.
 * @param <E> ..
 */
class Customer<E> implements Runnable {

    private final TaskOrder<E> currentValue;
    private final String name;
    private final int tasksNumber;

    /**
     * Constructor.
     * @param currentValue ..
     * @param name ..
     */
    Customer(TaskOrder<E> currentValue, String name, int iterations) {
        this.currentValue = currentValue;
        this.name = name;
        this.tasksNumber = iterations;
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
        E val;
        for (int i = 0; i < this.tasksNumber; i++) {
            val = this.currentValue.get();
            System.out.println(String.format("Customer %s gets value %s", this.name, val));
        }

    }

}
