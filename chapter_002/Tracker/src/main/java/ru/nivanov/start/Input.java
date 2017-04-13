package ru.nivanov.start;

import java.util.ArrayList;

/**
 * Input interface.
 * @author nivanov.
 */
interface Input {
    /**
     * Wait for user input.
     * @param question ..
     * @return ..
     */
    String ask(String question);

    /**
     * Overloads user input.
     * @param question ..
     * @param range ..
     * @return ..
     */
    int ask(String question, ArrayList<Integer> range);
}