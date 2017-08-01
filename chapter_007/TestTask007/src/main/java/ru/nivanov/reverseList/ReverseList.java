package ru.nivanov.reverseList;

/**
 * Created by Nikolay Ivanov on 01.08.2017.
 */
public class ReverseList {

    private static Node<Integer> node;

    /**
     * Main method.
     * @param args ..
     */
    public static void main(String[] args) {
        final int size = 10;

        ReverseList list = new ReverseList();

        Node<Integer> result = list.createList(node, size);

        System.out.println("Direct order:");
        list.display(result);

        System.out.println("Normal list, reverse print:");

        list.reverseDisplay(result);

        System.out.println("Reverse list, normal print:");

        Node<Integer> reverseNode = list.reverseList(result);
        list.display(reverseNode);
    }

    /**
     * Creates list.
     * @param integerNode ..
     * @param size ..
     * @return result.
     */
    Node<Integer> createList(Node<Integer> integerNode, int size) {

        integerNode = new Node<>(null, 0);

        Node<Integer> current = integerNode;

        for (int i = 1; i < size; i++) {
            current.next = new Node<>(null, i);
            current = current.getNext();
        }
        return integerNode;
    }

    /**
     * Prints list values in direct order..
     * @param value ..
     */
    private void display(Node<Integer> value) {
        Node<Integer> current = value;
        do {
            System.out.println(current.getValue());
            current = current.getNext();
        } while (current != null);
    }

    /**
     * Prints list in reverse order.
     * @param integerNode ..
     */
    private void reverseDisplay(Node<Integer> integerNode) {

        if (integerNode.getNext() != null) {
            reverseDisplay(integerNode.getNext());
        }
        System.out.println(integerNode.getValue());
    }

    /**
     * Reverse list.
     * @param val ..
     * @return result.
     */
    Node<Integer> reverseList(Node<Integer> val) {

        Node<Integer> reversed = null;
        Node<Integer> current = val;
        Node<Integer> nextNode;

        while (current != null) {

            nextNode = current.getNext();
            current.next = reversed;
            reversed = current;
            current = nextNode;
        }
        val = reversed;
        return val;
    }

    /**
     * Inner class node.
     * @param <T> ..
     */
    class Node<T> {
        private final T value;
        private Node<T> next;

        /**
         * Constructor.
         * @param next ..
         * @param value ..
         */
        Node(Node<T> next, T value) {
            this.next = next;
            this.value = value;
        }

        /**
         * Value getter.
         * @return value.
         */
        T getValue() {
            return value;
        }

        /**
         * Next node getter.
         * @return next node.
         */
        Node<T> getNext() {
            return next;
        }
    }
}
