package ru.nivanov;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by Nikolay Ivanov on 17.05.2017.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;

    /**
     * Constructor.
     * @param root ..
     */
    public Tree(Node<E> root) {
        this.root = root;
    }

    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return result.
     */
    public boolean add(Node<E> parent, Node<E> child) {
        boolean result = false;
        if (compare(parent, root) == 0) {
            root.childen.add(child);
            root.visitResult = false;
            return true;
        } else {
            Node<E> current;
            while (iterator().hasNext()) {
                if (compare(current = iterator().next(), parent) == 0) {
                    current.childen.add(child);
                    current.visitResult = false;
                    result = true;
                    break;
                }
            }
        }
        markAllUnvisited(root);
        return result;
    }

    /**
     * Marks all nodes unvisited.
     * @param currentNode ..
     */
    public void markAllUnvisited(Node<E> currentNode) {
        currentNode.visitResult = false;

        for (Node<E> value : currentNode.childen) {
            if (value.childen.size() != 0) {
                value.visitResult = false;
                markAllUnvisited(value);
            } else {
                value.visitResult = false;
            }
        }

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
     * Returns an iterator over elements of type {@code T}.
     * @return an Iterator.
     */
    @Override
    public Iterator<Node<E>> iterator() {
        return new TreeIterator();
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
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return !root.visitResult;
        }

        /**
         * Returns the next element in the iteration.
         * @return the next element in the iteration
         * @throws NoSuchElementException ..
         */
        @Override
        public Node<E> next() {
            while (!tempList.isEmpty()) {
                for (Node<E> node : currentRoot.childen) {
                    if (node.childen.size() == 0 && !node.visitResult) {
                        node.visitResult = true;
                        currentRoot = tempList.getLast();
                        return node;

                    } else if (node.childen.size() > 0 && !node.visitResult) {
                        currentRoot = node;
                        tempList.add(currentRoot);
                        break;
                    }
                }
                currentRoot = tempList.getLast();
                if (currentRoot.childen.getLast().visitResult) {
                    Node<E> result = tempList.removeLast();
                    result.visitResult = true;
                    if (tempList.size() != 0) {
                        currentRoot = tempList.getLast();
                        return result;
                    }
                    return result;
                }
            }
            throw new NoSuchElementException();

        }

    }

}