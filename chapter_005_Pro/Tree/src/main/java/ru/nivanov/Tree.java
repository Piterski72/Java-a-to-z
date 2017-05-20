package ru.nivanov;

import java.util.*;

/**
 * Created by Nikolay Ivanov on 17.05.2017.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;
    private Set<Node<E>> nodeSet;

    /**
     * Constructor.
     * @param root ..
     */
    public Tree(Node<E> root) {
        this.root = root;
        nodeSet = new HashSet<>();
    }

    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return result.
     */
    @Override
    public boolean add(Node<E> parent, Node<E> child) {

        Node<E> val = new Node<E>(null);

        if (compare(parent, root) == 0) {
            root.childen.add(child);
            return true;
        } else {
            val = searchNode(parent);
            System.out.println(val.value);

            val.childen.add(child);
            return true;
        }

    }

    /**
     * Search node.
     * @param value ..
     * @return result.
     */
    public Node<E> searchNode(Node<E> value) {
        Node<E> current = new Node<E>(null);
        while (this.iterator().hasNext()) {
            current = this.iterator().next();
            if (compare(value, current) == 0) {
                break;
            }
        }
        return current;


    }

    /**
     * Returns an iterator over elements of type {@code T}.
     * @return an Iterator.
     */
    @Override
    public Iterator<Node<E>> iterator() {
        return new TreeIterator();
    }

    /**
     * Compare method.
     * @param one ..
     * @param two ..
     * @return result ..
     */
    public int compare(Node<E> one, Node<E> two) {
        return one.value.compareTo(two.value);
    }

    /**
     * Iterator class.
     */
    class TreeIterator implements Iterator<Node<E>> {
        LinkedList<Node<E>> tempList = new LinkedList<>();
        Node<E> currentRoot;

        /**
         * Constructor.
         */
        public TreeIterator() {
            currentRoot = root;
            tempList.addLast(root);
            if (nodeSet != null || !nodeSet.isEmpty()) {
                for (Node<E> value : nodeSet) {
                    value.visitResult = false;
                }
            }
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            for (Node<E> item : root.childen) {
                if (!item.visitResult) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Returns the next element in the iteration.
         * @return the next element in the iteration
         * @throws NoSuchElementException ..
         */
        @Override
        public Node<E> next() {

            while (!tempList.isEmpty()) {

                Iterator<Node<E>> iter = currentRoot.childen.iterator();

                while (iter.hasNext()) {

                    currentRoot = iter.next();

                    if (!currentRoot.visitResult) {
                        tempList.addLast(currentRoot);
                        break;
                    }
                }

                if (currentRoot.childen.isEmpty() || !iter.hasNext()) {
                    Node<E> visit = tempList.removeLast();
                    visit.visitResult = true;
                    nodeSet.add(visit);
                    currentRoot = tempList.getLast();

                    return visit;
                }

            }
            throw new NoSuchElementException();

        }
    }


}