package ru.nivanov;

import java.util.Iterator;

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
        return ((Integer) next() != 0);
    }

    /**
     * Returns the next element in the iteration.
     * @return the next element in the iteration
     */
    @Override
    public Object next() {
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
        return 0;
    }
}
