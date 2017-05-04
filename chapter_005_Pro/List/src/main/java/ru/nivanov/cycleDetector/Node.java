package ru.nivanov.cycleDetector;

/**
 * Created by Nikolay Ivanov on 04.05.2017.
 * @param <T> ..
 */
class Node<T> {
    private final T value;
    private Node<T> next;

    /**
     * Constructor.
     * @param value ..
     */
    Node(T value) {
        this.value = value;
    }

    /**
     * Next getter.
     * @return next field.
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Node setter.
     * @param next ..
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}
