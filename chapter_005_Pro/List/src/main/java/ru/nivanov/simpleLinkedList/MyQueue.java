package ru.nivanov.simpleLinkedList;

/**
 * Created by Nikolay Ivanov on 03.05.2017.
 * @param <E> ..
 */
interface MyQueue<E> extends Iterable<E> {
    /**
     * Adding element.
     * @param e ..
     */
    void push(E e);

    /**
     * Gets element.
     * @return ..
     */
    E pop();
}
