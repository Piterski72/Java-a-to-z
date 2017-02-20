package ru.nivanov;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Nikolay Ivanov on 20.02.2017.
 */
public class ConsoleIO implements IO {
    private final Scanner scanner;
    private final PrintStream output;

    /**
     * Constructor.
     * @param scanner ..
     * @param output ..
     */
    public ConsoleIO(final Scanner scanner, final PrintStream output) {
        this.scanner = scanner;
        this.output = output;
    }

    /**
     * Read method.
     * @return ..
     */
    public String read() {
        return this.scanner.next();
    }

    /**
     * Print method.
     * @param value ..
     */
    public void println(Object value) {
        this.output.println(value);
    }
}
