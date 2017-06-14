package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 14.06.2017.
 */
public class MemoryUsage {

    /**
     * Main method.
     * @param args ..
     */
    public static void main(String[] args) {
        System.out.println("start");

        info();

        User[] users = new User[103941];

        for (int i = 0; i < users.length; i++) {
            users[i] = new User("test" + i);
        }

        System.out.println("finish");
        info();

    }

    /**
     * Displays info about memory usage.
     */
    public static void info() {
        int kb = 1024;
        Runtime runtime = Runtime.getRuntime();
        System.out.println(String.format("Max memory b is %,d", runtime.maxMemory()));
        System.out.println(String.format("Total memory b is %,d", runtime.totalMemory()));
        System.out.println(String.format("Free memory b is %,d", runtime.freeMemory()));
        System.out.println(String.format("Used memory b is %,d", (runtime.totalMemory() - runtime.freeMemory())));


    }
}
