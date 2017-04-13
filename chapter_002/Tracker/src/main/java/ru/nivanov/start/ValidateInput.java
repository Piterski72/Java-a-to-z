package ru.nivanov.start;

import java.util.ArrayList;

/**
 * ValidateInput class.
 * @author nivanov.
 */
public class ValidateInput extends ConsoleInput {
    /**
     * int ask method override.
     * @param question ..
     * @param range ..
     * @return value ..
     */
    public int ask(String question, ArrayList<Integer> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select key from menu (0 - 5): ");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter correct number (0 - 5): ");
            }
        } while (invalid);
        return value;
    }
}