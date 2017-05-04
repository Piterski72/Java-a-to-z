package ru.nivanov.simpleSet;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Nikolay Ivanov on 04.05.2017.
 */
class LinkedListSet<E> implements Iterator<E> {

    private LinkedList<E> linkedList;
    private Iterator<E> innerIterator;

    /**
     * Constructor.
     */
    public LinkedListSet() {
        this.linkedList = new LinkedList<>();

    }

    /**
     * Add object.
     * @param element ..
     */
    void add(E element) {
        if (checkForUnique(element)) {
            this.linkedList.add(element);
            innerIterator = this.linkedList.iterator();
        }
    }

    /**
     * Check for duplicates.
     * @param elem ..
     * @return ..
     */
    private boolean checkForUnique(E elem) {
        if (elem != null) {
            for (E aLinkedList : this.linkedList) {
                if (aLinkedList.equals(elem)) {
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
        return this.innerIterator.hasNext();
    }

    /**
     * Returns the next element in the iteration.
     * @return the next element in the iteration
     */
    @Override
    public E next() {
        return this.innerIterator.next();
    }
}
