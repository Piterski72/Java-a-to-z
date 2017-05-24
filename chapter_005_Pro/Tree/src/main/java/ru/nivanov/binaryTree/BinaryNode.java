package ru.nivanov.binaryTree;

/**
 * Created by Nikolay Ivanov on 24.05.2017.
 * @param <E> ..
 */
public class BinaryNode<E> {
    private BinaryNode<E> leftChild;
    private BinaryNode<E> rightChild;
    private E value;

    /**
     * Constructor.
     * @param value ..
     */
    public BinaryNode(E value) {
        this.value = value;
    }

    /**
     * Value getter.
     * @return value.
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

    /**
     * Left child getter.
     * @return ..
     */
    public BinaryNode<E> getLeftChild() {
        return leftChild;
    }

    /**
     * Left child setter.
     * @param leftChild ..
     */
    public void setLeftChild(BinaryNode<E> leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * Right child getter.
     * @return ..
     */
    public BinaryNode<E> getRightChild() {
        return rightChild;
    }

    /**
     * Right child setter.
     * @param rightChild ..
     */
    public void setRightChild(BinaryNode<E> rightChild) {
        this.rightChild = rightChild;
    }

}
