package ru.nivanov.simpleListThreadSafe;

import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Nikolay Ivanov on 03.05.2017.
 * @param <E> ..
 */

@ThreadSafe
public class LinkedListContainerThreadSafe<E> implements SimpleLinkedContainer<E> {

    private Node first;
    private Node last;
    private int size;

    /**
     * Size getter.
     * @return size
     */
    synchronized int getSize() {
        return size;
    }

    /**
     * Add method.
     * @param value ..
     */
    @Override
    public synchronized void add(E value) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    /**
     * Get by index method.
     * @param index ..
     * @return ..
     */
    @Override
    public synchronized E get(int index) {
        if (index >= 0 && index < size) {
            return node(index).item;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            private Node<E> lastReturned;
            private Node<E> next = first;
            private int nextIndex;

            @Override
            public boolean hasNext() {
                return nextIndex < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    lastReturned = next;
                    next = next.next;
                    nextIndex++;
                    return lastReturned.item;
                }
            }
        };
    }

    /**
     * Returns the (non-null) Node at the specified element index.
     * @param index ..
     * @return ..
     */
    synchronized Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    /**
     * Inner class node.
     * @param <E> ..
     */
    private class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;

        /**
         * Constructor.
         * @param prev ..
         * @param element ..
         * @param next ..
         */
        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }


}
