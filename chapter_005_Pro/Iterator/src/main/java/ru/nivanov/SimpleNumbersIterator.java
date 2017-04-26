package ru.nivanov;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Nikolay Ivanov on 25.04.2017.
 */
class SimpleNumbersIterator implements Iterator {
    private final int[] values;
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
        return (returnNextSimpleNumberIndex() != -1);

    }

    /**
     * Returns the next element in the iteration.
     * @return the next element in the iteration
     * @throws java.util.NoSuchElementException ..
     */
    @Override
    public Object next() throws NoSuchElementException {
        try {
            index = returnNextSimpleNumberIndex();
            return values[index++];
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new NoSuchElementException("no such element");
        }
    }

    /**
     * Returns next index of simple number in array. Returns -1 if no such element found.
     * @return index
     */
    private int returnNextSimpleNumberIndex() {
        for (int i = index; i < values.length; i++) {
            final int three = 3;
            if (values[i] == 1 || values[i] == 2 || values[i] == three) {
                return i;
            } else if (values[i] % 2 != 0) {
                boolean foundSimple = true;
                for (int j = three; j < values[i]; j++) {
                    if (values[i] % j == 0) {
                        foundSimple = false;
                        break;
                    }
                }
                if (foundSimple) {
                    return i;
                }
            }
        }
        return -1;
    }
}
