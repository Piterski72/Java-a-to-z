package ru.nivanov.simpleLinkedList;

/**
 * Created by Nikolay Ivanov on 03.05.2017.
 * @param <E> ..
 */
public class StackContainer<E> extends LinkedListContainer<E> implements MyQueue<E> {

    /**
     * Adding element.
     * @param e ..
     */
    @Override
    public void push(E e) {
        super.add(e);
    }

    /**
     * REmoves and returns last added element.
     * @return ..
     */
    @Override
    public E pop() {
        return super.unlink(super.node(super.getSize() - 1));
    }
}
