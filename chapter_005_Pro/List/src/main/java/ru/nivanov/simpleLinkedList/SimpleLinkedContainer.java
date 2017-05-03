package ru.nivanov.simpleLinkedList;

/**
 * Created by Nikolay Ivanov on 03.05.2017.
 * @param <E> ..
 */
interface SimpleLinkedContainer<E> extends Iterable<E> {
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
