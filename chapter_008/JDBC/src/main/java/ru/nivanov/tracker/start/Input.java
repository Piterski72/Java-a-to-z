package ru.nivanov.tracker.start;

import java.util.ArrayList;

/**
 * input interface.
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