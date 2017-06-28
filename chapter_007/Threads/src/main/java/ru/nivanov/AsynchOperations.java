package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 26.06.2017.
 */
public class AsynchOperations {
    private static String input = "this is a test string line for counting words and spaces in it";

    /**
     * Main method.
     * @param args ..
     */
    public static void main(String[] args) {
        System.out.println("Start");

        long start = System.currentTimeMillis();

        Thread words = new Thread() {
            /**
             * If this thread was constructed using a separate
             * <code>Runnable</code> run object, then that
             * <code>Runnable</code> object's <code>run</code> method is called;
             * otherwise, this method does nothing and returns.
             * <p>
             * Subclasses of <code>Thread</code> should override this method.
             * @see #start()
             * @see #stop()
             */
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().getName() + "interrupted!");
                    return;
                }
                TextProcessor txt = new TextProcessor(input);
                System.out.println(String.format("words: %d", txt.wordsCount()));
            }
        };
        Thread spaces = new Thread() {
            /**
             * If this thread was constructed using a separate
             * <code>Runnable</code> run object, then that
             * <code>Runnable</code> object's <code>run</code> method is called;
             * otherwise, this method does nothing and returns.
             * <p>
             * Subclasses of <code>Thread</code> should override this method.
             * @see #start()
             * @see #stop()
             */
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().getName() + "interrupted!");
                    return;
                }
                TextProcessor txt = new TextProcessor(input);
                System.out.println(String.format("spaces: %d", txt.spacesCount()));
            }
        };

        words.start();
        spaces.start();

        //stopAllThreads();

        long end = System.currentTimeMillis();
        while ((end - start) < 1000) {
            end = System.currentTimeMillis();
        }

        if ((end - start) >= 1000) {
            System.out.println("Interruption begins!");
            words.interrupt();
            spaces.interrupt();
        }

        try {
            words.join();
            spaces.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finish");
    }

    /**
     * Stops all active threads.
     */
    public static void stopAllThreads() {
        System.out.println("stopping all...");
        Thread[] array = new Thread[10];
        Thread.enumerate(array);
        for (Thread value : array) {
            value.interrupt();
        }
    }
}
