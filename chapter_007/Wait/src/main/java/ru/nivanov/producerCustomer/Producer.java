package ru.nivanov.producerCustomer;

/**
 * Created by Nikolay Ivanov on 12.07.2017.
 * @param <E> ..
 */
class Producer<E> implements Runnable {

    private final TaskOrder<E> taskOrder;
    private final String name;
    private final int tasksNumber;

    /**
     * Constructor.
     * @param taskOrder ..
     * @param name ..
     */
    Producer(TaskOrder<E> taskOrder, String name, int iters) {
        this.taskOrder = taskOrder;
        this.name = name;
        this.tasksNumber = iters;
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
        E item;
        for (int i = 0; i < this.tasksNumber; i++) {
            item = (E) String.format("task# %d", i);
            this.taskOrder.put(item);
            System.out.println(String.format("Producer %s put item %s", this.name, item));

        }
    }
}
