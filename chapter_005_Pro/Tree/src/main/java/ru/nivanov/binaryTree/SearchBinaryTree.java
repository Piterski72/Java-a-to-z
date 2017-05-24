package ru.nivanov.binaryTree;

/**
 * Created by Nikolay Ivanov on 24.05.2017.
 * @param <E> ..
 */
class SearchBinaryTree<E extends Comparable<E>> {
    private BinaryNode<E> root;

    /**
     * Constructor.
     * @param root ..
     */
    SearchBinaryTree(BinaryNode<E> root) {
        this.root = root;
    }

    /**
     * Insert new node in binary tree.
     * @param element ..
     */
    public void add(E element) {
        BinaryNode<E> newNode = new BinaryNode<>(element);
        BinaryNode<E> current = root;
        BinaryNode<E> parent = new BinaryNode<>(null);

        while (current != null) {
            if (element.compareTo(current.getValue()) <= 0) {
                parent = current;
                current = current.getLeftChild();

            } else {
                parent = current;
                current = current.getRightChild();
            }
        }
        if (element.compareTo(parent.getValue()) <= 0) {
            parent.setLeftChild(newNode);

        } else {
            parent.setRightChild(newNode);
        }
    }

    /**
     * Search by value.
     * @param element ..
     * @return ..
     */
    public BinaryNode<E> searchByValue(E element) {
        BinaryNode<E> current = root;

        while (current.getValue().compareTo(element) != 0) {
            if (element.compareTo(current.getValue()) > 0) {
                current = current.getRightChild();
            } else {
                current = current.getLeftChild();
            }
            if (current == null) {
                return new BinaryNode<>(null);
            }
        }
        return current;
    }
}
