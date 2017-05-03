package ru.nivanov.simpleLinkedList;

/**
 * Created by Nikolay Ivanov on 03.05.2017.
 * @param <E> ..
 */
public class QueueContainer<E> extends LinkedListContainer<E> implements MyQueue<E> {

    /**
     * Adding element.
     * @param e ..
     */
    @Override
    public void push(E e) {
        super.add(e);
    }

    /**
     * Removes element from head of queue and returns it.
     * @return ..
     */
    @Override
    public E pop() {
        return super.unlink(super.node(0));
    }
}
