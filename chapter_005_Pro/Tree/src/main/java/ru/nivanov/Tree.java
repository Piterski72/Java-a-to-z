package ru.nivanov;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by Nikolay Ivanov on 17.05.2017.
 * @param <E> ..
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;

    /**
     * Constructor.
     * @param root ..
     */
    Tree(Node<E> root) {
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
            root.getChilden().add(child);
            root.setVisitResult(false);
            return true;
        } else {
            Node<E> current;
            while (iterator().hasNext()) {
                if (compare(current = iterator().next(), parent) == 0) {
                    current.getChilden().add(child);
                    current.setVisitResult(false);
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
    private void markAllUnvisited(Node<E> currentNode) {
        currentNode.setVisitResult(false);

        for (Node<E> value : currentNode.getChilden()) {
            if (value.getChilden().size() != 0) {
                value.setVisitResult(false);
                markAllUnvisited(value);
            } else {
                value.setVisitResult(false);
            }
        }

    }

    /**
     * Compare method.
     * @param one ..
     * @param two ..
     * @return result ..
     */
    private int compare(Node<E> one, Node<E> two) {
        return one.getValue().compareTo(two.getValue());
    }

    /**
     * Detects if tree is binary tree.
     * @return result
     */
    public boolean isBinary() {
        markAllUnvisited(root);
        while (iterator().hasNext()) {
            if (iterator().next().getChilden().size() > 2) {
                return false;
            }
        }
        return true;
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
        private LinkedList<Node<E>> tempList = new LinkedList<>();
        private Node<E> currentRoot;

        /**
         * Constructor.
         */
        TreeIterator() {
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
            return !root.isVisitResult();
        }

        /**
         * Returns the next element in the iteration.
         * @return the next element in the iteration
         * @throws NoSuchElementException ..
         */
        @Override
        public Node<E> next() {
            while (!tempList.isEmpty()) {
                for (Node<E> node : currentRoot.getChilden()) {
                    if (node.getChilden().size() == 0 && !node.isVisitResult()) {
                        node.setVisitResult(true);
                        currentRoot = tempList.getLast();
                        return node;

                    } else if (node.getChilden().size() > 0 && !node.isVisitResult()) {
                        currentRoot = node;
                        tempList.add(currentRoot);
                        break;
                    }
                }
                currentRoot = tempList.getLast();
                if (currentRoot.getChilden().getLast().isVisitResult()) {
                    Node<E> result = tempList.removeLast();
                    result.setVisitResult(true);
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