package ru.nivanov.Output;

/**
 * Created by Nikolay Ivanov on 27.03.2017.
 */
public class ConsoleOutput implements Output {
    /**
     * outputs string.
     * @param output String to write.
     */
    public void write(String output) {
        System.out.println(output);
    }
}
