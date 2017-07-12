package ru.nivanov.simpleListThreadSafe;

/**
 * Created by Nikolay Ivanov on 02.05.2017.
 * @param <E> ..
 */
interface SimpleContainer<E> extends Iterable<E> {

    /**
     * Add method.
     * @param value ..
     */
    void add(E value);

    /**
     * Get by index method.
     * @param index ..
     * @return ..
     */
    E get(int index);
}
