package ru.nivanov.start;

import java.util.ArrayList;

/**
 * StubInput class.
 * @author nivanov.
 */
public class StubInput implements Input {
    private final ArrayList<String> answers;
    private int position = 0;

    public StubInput(ArrayList<String> answers) {
        this.answers = answers;
    }

    /**
     * Ask realization.
     * @param question ..
     * @return answers ..
     */
    public String ask(String question) {
        return answers.get(position++);
    }

    /**
     * ask method.
     * @param question ..
     * @param range ..
     * @return value ..
     */
    public int ask(String question, ArrayList<Integer> range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;

        if (range.stream().anyMatch(p -> p == key)) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range");
        }
    }
}