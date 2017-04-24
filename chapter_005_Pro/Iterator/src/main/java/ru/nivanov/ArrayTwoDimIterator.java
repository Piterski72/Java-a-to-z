package ru.nivanov;

import java.util.Iterator;

/**
 * Created by Nikolay Ivanov on 24.04.2017.
 */
class ArrayTwoDimIterator implements Iterator {
    private final int[][] values;
    private int horIndex = 0;
    private int vertIndex = 0;

    /**
     * Constructor.
     * @param values ..
     */
    public ArrayTwoDimIterator(int[][] values) {
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
        return ((this.values[0].length + this.values.length - 1) > (horIndex + vertIndex));
    }

    /**
     * Returns the next element in the iteration.
     * @return the next element in the iteration
     */
    @Override
    public Object next() {
        if (vertIndex < values.length) {
            return values[horIndex][vertIndex++];
        } else {
            horIndex++;
            vertIndex = 0;
            return values[horIndex][vertIndex++];
        }

    }
}
