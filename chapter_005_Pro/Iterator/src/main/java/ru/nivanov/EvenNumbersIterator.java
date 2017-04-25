package ru.nivanov;

import java.util.Iterator;

/**
 * Created by Nikolay Ivanov on 24.04.2017.
 */
public class EvenNumbersIterator implements Iterator {
    private final int[] values;
    private int index = 0;
    private int evenResult = 0;

    /**
     * Constructor.
     * @param values ..
     */
    EvenNumbersIterator(int[] values) {
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
        return (evenResult != -1);
    }

    /**
     * Returns the next element in the iteration.
     * @return the next element in the iteration
     */
    @Override
    public Object next() {

        while (index < values.length) {
            if (values[index] % 2 == 0) {
                evenResult = values[index];
                index++;
                return evenResult;
            } else {
                index++;
            }
        }
        return evenResult = -1;
    }


}
