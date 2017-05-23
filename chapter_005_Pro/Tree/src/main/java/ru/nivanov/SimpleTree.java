package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 17.05.2017.
 */
interface SimpleTree<E extends Comparable<E>> extends Iterable<Node<E>> {
    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return result.
     */
    boolean add(Node<E> parent, Node<E> child);

}
