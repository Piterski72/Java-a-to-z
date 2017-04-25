package ru.nivanov;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Nikolay Ivanov on 25.04.2017.
 */
class SimpleNumbersIterator implements Iterator {
    private final int[] values;
    private final int three = 3;
    private int index = 0;

    /**
     * Constructor.
     * @param values ..
     */
    SimpleNumbersIterator(int[] values) {
        this.values = values;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return index < values.length && (!checkForNoSimpleNumbersRemaining());

    }

    /**
     * Returns the next element in the iteration.
     * @return the next element in the iteration
     * @throws java.util.NoSuchElementException ..
     */
    @Override
    public Object next() throws NoSuchElementException {
        while (index < values.length) {
            int result;
            if (values[index] == 1 || values[index] == 2 || values[index] == three) {
                result = values[index];
                index++;
                return result;
            } else if (values[index] % 2 != 0) {
                boolean foundSimple = true;
                for (int i = three; i < values[index]; i++) {
                    if (values[index] % i == 0) {
                        foundSimple = false;
                        break;
                    }
                }
                if (foundSimple) {
                    result = values[index];
                    index++;
                    return result;
                }
            }
            index++;
        }
        throw new NoSuchElementException();
    }

    /**
     * Checks for simple numbers remaining.
     * @return result.
     */
    private boolean checkForNoSimpleNumbersRemaining() {
        boolean endOfIteration = false;
        for (int i = index; i < values.length; i++) {
            if (values[i] == 1 || values[i] == 2 || values[i] == three) {
                return false;
            } else if (values[i] % 2 != 0) {
                int matches = 0;
                for (int j = three; j < values[i]; j++) {
                    if (values[i] % j == 0) {
                        System.out.println("!!");
                        endOfIteration = true;
                    } else {
                        matches++;
                    }
                }
                if (matches == (values[i] - three)) {
                    return false;
                }
            } else if (values[i] % 2 == 0) {
                endOfIteration = true;
            }
        }
        return endOfIteration;
    }
}
