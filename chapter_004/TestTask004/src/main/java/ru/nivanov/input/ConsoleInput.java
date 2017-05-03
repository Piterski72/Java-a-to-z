package ru.nivanov.input;

import java.util.Scanner;

/**
 * Created by Nikolay Ivanov on 27.03.2017.
 */
public class ConsoleInput implements Input {
    /**
     * inner instance of Scanner.
     */
    private Scanner sc = new Scanner(System.in);

    /**
     * Returns line read from console.
     * @return String read from console.
     */
    @Override
    public String read() {
        return sc.nextLine();
    }
}
