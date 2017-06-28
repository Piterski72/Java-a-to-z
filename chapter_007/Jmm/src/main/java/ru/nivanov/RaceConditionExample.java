package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 28.06.2017.
 * Example of race conditions in non-safety threads.
 */
public class RaceConditionExample implements Runnable {
    private static StringFiller stringFiller;

    /**
     * Constructor.
     * @param value ..
     */
    private RaceConditionExample(StringFiller value) {
        stringFiller = value;
    }

    /**
     * Main method.
     * @param args ..
     */
    public static void main(String[] args) {
        RaceConditionExample badExample = new RaceConditionExample(new StringFiller());
        Thread one = new Thread(badExample, "one");
        Thread two = new Thread(badExample, "two");
        one.start();
        two.start();

        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(stringFiller.getStringBuilder().toString());


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
        for (int i = 0; i < 10; i++) {
            String insert = "test" + i;

            if (!stringFiller.checkForDuplicate(insert)) {
                stringFiller.add(insert);
            }

        }
    }

    /**
     * Class StringFiller.
     */
    public static class StringFiller {
        StringBuilder stringBuilder = new StringBuilder();

        /**
         * Add string value.
         * @param val ..
         */
        void add(String val) {
            getStringBuilder().append(val);
            getStringBuilder().append(System.getProperty("line.separator"));
        }

        /**
         * Checking for duplicates in string builder.
         * @param text ..
         * @return ..
         */
        boolean checkForDuplicate(String text) {
            return getStringBuilder().toString().contains(text);

        }

        /**
         * Getter.
         * @return ..
         */
        StringBuilder getStringBuilder() {
            return stringBuilder;
        }
    }

}
