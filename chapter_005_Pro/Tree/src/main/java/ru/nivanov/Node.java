package ru.nivanov;

import java.util.LinkedList;

/**
 * Created by Nikolay Ivanov on 18.05.2017.
 * @param <E> ..
 */
public class Node<E> {

    private LinkedList<Node<E>> childen;
    private boolean visitResult;
    private E value;

    /**
     * Constructor.
     * @param value ..
     */
    public Node(E value) {
        this.childen = new LinkedList<>();
        this.value = value;
        this.visitResult = false;
    }

    /**
     * Children list getter.
     * @return ..
     */
    public LinkedList<Node<E>> getChilden() {
        return childen;
    }

    /**
     * Children setter.
     * @param childen ..
     */
    public void setChilden(LinkedList<Node<E>> childen) {
        this.childen = childen;
    }

    /**
     * Result of visiting getter.
     * @return ..
     */
    public boolean isVisitResult() {
        return visitResult;
    }

    /**
     * Visit result setter.
     * @param visitResult ..
     */
    public void setVisitResult(boolean visitResult) {
        this.visitResult = visitResult;
    }

    /**
     * Value getter.
     * @return ..
     */
    public E getValue() {
        return value;
    }

    /**
     * Value setter.
     * @param value ..
     */
    public void setValue(E value) {
        this.value = value;
    }
}
