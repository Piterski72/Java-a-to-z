package ru.nivanov.apartments;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Nikolay Ivanov on 12.05.2017.
 * @param <T> ..
 * @param <V> ..
 */
public class ApartmentOwners<T, V> implements Iterator {
    private static final int FOUR = 4;
    private static final float LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << FOUR; // aka 16
    private BaseInfo<T, V>[] innerArray;
    private int count = 0;
    private int position = 0;

    /**
     * Constructor with default size of inner array.
     */
    public ApartmentOwners() {
        innerArray = new BaseInfo[DEFAULT_INITIAL_CAPACITY];
    }

    /**
     * Constructor for user size of inner array.
     */
    public ApartmentOwners(int size) {
        if (size > 2) {
            innerArray = (BaseInfo<T, V>[]) new BaseInfo[size];
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Add new key-value mapping.
     * @param key ..
     * @param value ..
     * @return ..
     */
    public boolean insert(T key, V value) {
        if (count >= ((int) (innerArray.length * LOAD_FACTOR))) {
            resize();
        }
        int i = key.hashCode() & (innerArray.length - 1);
        if (innerArray[i] == null) {
            innerArray[i] = (BaseInfo<T, V>) new BaseInfo(key.hashCode(), key, value);
            count++;
            return true;
        } else {
            if (innerArray[i].getKey().hashCode() == key.hashCode() && (key != null && innerArray[i].getKey().equals(
                    key) || innerArray[i].getKey() == key)) {
                innerArray[i].setValue(value);
                return true;
            }
        }
        return false;
    }

    /**
     * Resize array.
     */
    private void resize() {
        BaseInfo<T, V>[] oldArray = innerArray;
        BaseInfo<T, V>[] newArray = (BaseInfo<T, V>[]) new BaseInfo[innerArray.length * 2];
        BaseInfo<T, V> current;
        for (int j = 0; j < oldArray.length; j++) {
            current = oldArray[j];
            newArray[(newArray.length - 1) & current.getKey().hashCode()] = current;
            oldArray[j] = null;

        }
        innerArray = newArray;
    }

    /**
     * Get value by key.
     * @param key ..
     * @return ..
     */
    public V get(T key) {
        int length = innerArray.length;
        BaseInfo<T, V> found = innerArray[key.hashCode() & (length - 1)];
        if (innerArray != null && length > 0 && found != null) {
            if (found.getKey().hashCode() == key.hashCode() && (key != null && found.getKey().equals(
                    key) || found.getKey() == key)) {
                return found.getValue();
            }
        }
        return null;

    }

    /**
     * Delete key.
     * @param key ..
     * @return ..
     */
    public boolean delete(T key) {
        int length = innerArray.length;
        int i = key.hashCode() & (length - 1);
        BaseInfo<T, V> forDelete = innerArray[i];
        if (innerArray != null && length > 0 && forDelete != null) {
            if (forDelete.getKey().hashCode() == key.hashCode() && (key != null && forDelete.getKey().equals(
                    key) || forDelete.getKey() == key)) {
                innerArray[i] = null;
                count--;
                return true;
            }
        }

        return false;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        for (int i = position; i < innerArray.length; i++) {
            if (innerArray[i] != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the next element in the iteration.
     * @return the next element in the iteration
     */
    @Override
    public BaseInfo<T, V> next() throws NoSuchElementException {
        try {
            while (innerArray[position] == null) {
                position++;
            }
            return innerArray[position++];

        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new NoSuchElementException("no such element");
        }

    }
}
