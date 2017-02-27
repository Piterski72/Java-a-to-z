package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 20.02.2017.
 */
class Validator {

    private final IO io;

    /**
     * Constructor.
     * @param io ..
     */
    Validator(IO io) {
        this.io = io;
    }

    /**
     * Get double.
     * @param line ..
     * @return ..
     */
    double getDouble(String line) {
        boolean invalid;
        do {
            try {
                this.io.println(line);
                return Double.valueOf(this.io.read());
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
                invalid = true;
                this.io.println("error read double, try again");
            }
        } while (invalid);
        throw new UnsupportedOperationException();
    }

    /**
     * Get double.
     * @param line ..
     * @return ..
     */
    int getInt(String line) {
        boolean invalid;
        do {
            try {
                this.io.println(line);
                return Integer.valueOf(this.io.read());
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
                invalid = true;
                this.io.println("error read int, try again");
            }
        } while (invalid);
        throw new UnsupportedOperationException();
    }

    /**
     * Get user input.
     * @param line ..
     * @return ..
     */
    String getString(String line) {
        io.println(line);
        return this.io.read();
    }

    /**
     * Verify user input.
     * @param text ..
     * @param choice ..
     * @return ..
     */
    boolean compare(String text, String choice) {
        io.println(text);
        return (choice.equalsIgnoreCase(this.io.read()));
    }

    /**
     * Check range method.
     * @param digit ..
     * @param range ..
     * @return ..
     */
    boolean checkRange(int digit, int[] range) {
        boolean tested = false;
        for (int item : range) {
            if (digit == item) {
                tested = true;
            }
        }
        return tested;
    }
}
