package ru.nivanov.simpleListThreadSafe;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Nikolay Ivanov on 02.05.2017.
 * @param <E> ..
 */
public class ArrayContainer<E> implements SimpleContainer<E> {
    private final int defaultSize = 10;
    private Object[] container;
    private int size;
    private int position = 0;

    /**
     * Constructor.
     * @param size ..
     */
    ArrayContainer(int size) {
        if (size > 0) {
            this.size = size;
            this.container = new Object[size];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + size);
        }

    }

    /**
     * Constructor for default size..
     */
    public ArrayContainer() {
        size = defaultSize;
        this.container = new Object[size];
    }

    /**
     * Add object.
     * @param value ..
     */
    @Override
    public void add(E value) {
        if (this.position <= (this.container.length - 1)) {
            this.container[position++] = value;
        } else {
            this.container = Arrays.copyOf(this.container, (this.container.length + defaultSize));
            this.container[position++] = value;
            this.size = this.container.length;

        }
    }

    /**
     * Get object by index.
     * @param index ..
     * @return ..
     */
    @Override
    public E get(int index) {
        if (index < this.container.length && index >= 0) {
            return (E) this.container[index];
        } else {
            throw new IllegalArgumentException("index out of range: " + index);
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int cursor;       // index of next element to return
            private int lastRet = -1; // index of last element returned; -1 if no such

            @Override
            public boolean hasNext() {
                return cursor != position;
            }

            @Override
            public E next() {
                int i = cursor;
                if (i >= position) {
                    throw new NoSuchElementException();
                }
                cursor = i + 1;
                return (E) container[lastRet = i];
            }
        };
    }

    /**
     * Size getter.
     * @return ..
     */
    public int getSize() {
        return this.size;
    }
}
