package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 29.06.2017.
 */
public class CountThreads implements Runnable {
    private Count counter = null;

    /**
     * Constructor.
     * @param counter ..
     */
    private CountThreads(Count counter) {
        this.counter = counter;
    }

    /**
     * Main method.
     * @param args ..
     */
    public static void main(String[] args) {
        Count count = new Count();
        Thread one = new Thread(new CountThreads(count), "one");
        Thread two = new Thread(new CountThreads(count), "two");
        Thread three = new Thread(new CountThreads(count), "three");
        one.start();
        two.start();
        three.start();

        try {
            one.join();
            two.join();
            three.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finish");

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
        this.counter.increment();
    }

    /**
     * Class count.
     */
    public static final class Count {
        /**
         * @GuardedBy Count object.
         */
        int value = 0;

        /**
         * Increment method.
         */
        synchronized void increment() {
            this.value++;
            System.out.println(
                    String.format("thread %s incremented value, value is %d", Thread.currentThread().getName(),
                            getValue()));

        }

        /**
         * Getter for value.
         * @return ..
         */
        int getValue() {
            return value;
        }
    }

}
