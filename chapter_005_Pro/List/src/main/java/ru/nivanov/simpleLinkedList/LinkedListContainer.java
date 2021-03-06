package ru.nivanov.simpleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Nikolay Ivanov on 03.05.2017.
 * @param <E> ..
 */

public class LinkedListContainer<E> implements SimpleLinkedContainer<E> {

    private Node first;
    private Node last;
    private int size;

    /**
     * Size getter.
     * @return size
     */
    int getSize() {
        return size;
    }

    /**
     * Add method.
     * @param value ..
     */
    @Override
    public void add(E value) {
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
    public E get(int index) {
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
    Node<E> node(int index) {
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
     * Unlinks non-null node x.
     * @param x ..
     * @return ..
     */
    E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
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
