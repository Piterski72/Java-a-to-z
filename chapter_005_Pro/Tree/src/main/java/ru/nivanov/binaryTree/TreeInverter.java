package ru.nivanov.binaryTree;

import java.util.LinkedList;

/**
 * Created by Nikolay Ivanov on 19.06.2017.
 * @param <E> ..
 */
class TreeInverter<E extends Comparable<E>> {
    private LinkedList<BinaryNode<E>> queue = new LinkedList<>();
    private SearchBinaryTree<E> tree;

    /**
     * Constructor.
     * @param tree ..
     */
    TreeInverter(SearchBinaryTree<E> tree) {
        this.tree = tree;
    }

    /**
     * Inverting tree method (dfs).
     * @param node ..
     */
    public void invertTreeDfs(BinaryNode<E> node) {

        if (node != null) {

            invertTreeDfs(node.getLeftChild());
            invertTreeDfs(node.getRightChild());
            if (node.getLeftChild() != null || node.getRightChild() != null) {
                swapChildren(node);
            }
        }

    }

    /**
     * Inverting tree method (dfs).
     * @param node ..
     */
    public void invertTreeBfs(BinaryNode<E> node) {

        queue.addLast(node);
        while (!queue.isEmpty()) {
            BinaryNode<E> current = queue.removeFirst();

            if (current.getLeftChild() != null && current.getRightChild() != null) {

                queue.addLast(current.getLeftChild());
                queue.addLast(current.getRightChild());
                swapChildren(current);
            } else if (current.getLeftChild() == null && current.getRightChild() != null) {
                queue.addLast(current.getRightChild());
                swapChildren(current);
            } else if (current.getLeftChild() != null && current.getRightChild() == null) {
                queue.addLast(current.getLeftChild());
                swapChildren(current);
            }

        }

    }

    /**
     * Swap left and right children.
     * @param node ..
     */
    private void swapChildren(BinaryNode<E> node) {
        BinaryNode<E> temp = node.getLeftChild();
        node.setLeftChild(node.getRightChild());
        node.setRightChild(temp);
        temp = null;
    }


}
