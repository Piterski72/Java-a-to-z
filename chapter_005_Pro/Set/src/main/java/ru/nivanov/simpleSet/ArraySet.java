package ru.nivanov.simpleSet;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Nikolay Ivanov on 04.05.2017.
 * @param <E> ..
 */
class ArraySet<E> implements Iterator<E> {
    private final int defaultSize = 10;
    private Object[] container;
    private int size;
    private int position = 0;
    private int iteratorCursor;

    /**
     * Constructor.
     * @param size ..
     */
    ArraySet(int size) {
        if (size > 0) {
            this.size = size;
            this.container = new Object[size];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + size);
        }
    }

    /**
     * Constructor with default size.
     */
    ArraySet() {
        this.size = defaultSize;
        this.container = new Object[defaultSize];
    }

    /**
     * Add method.
     * @param element ..
     */
    public void add(E element) {
        if (checkForUnique(element)) {
            if (this.position <= (this.container.length - 1)) {
                this.container[position++] = element;
            } else {
                this.container = Arrays.copyOf(this.container, (this.container.length + defaultSize));
                this.container[position++] = element;
                this.size = this.container.length;

            }
        }
    }

    /**
     * Check for duplicates.
     * @param elem ..
     * @return ..
     */
    private boolean checkForUnique(E elem) {
        if (elem != null) {
            for (Object value : this.container) {
                if (elem.equals(value)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        if (iteratorCursor != position) {
            return true;
        } else {
            iteratorCursor = 0;
            return false;
        }
    }

    /**
     * Returns the next element in the iteration.
     * @return the next element in the iteration
     */
    @Override
    public E next() {
        int i = iteratorCursor;
        if (i >= position) {
            throw new NoSuchElementException();
        }
        iteratorCursor = i + 1;
        return (E) container[i];
    }
}
