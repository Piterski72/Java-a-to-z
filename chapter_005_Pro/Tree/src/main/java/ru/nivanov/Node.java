package ru.nivanov;

import java.util.LinkedList;

/**
 * Created by Nikolay Ivanov on 18.05.2017.
 */
public class Node<E> {

    LinkedList<Node<E>> childen;
    boolean visitResult;
    E value;

    /**
     * Constructor.
     * @param value ..
     */
    public Node(E value) {
        this.childen = new LinkedList<>();
        this.value = value;
        this.visitResult = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<?> node = (Node<?>) o;

        return childen.equals(node.childen) && value.equals(node.value);
    }

    @Override
    public int hashCode() {
        int result = 0;
        if (childen.size() == 0) {
            result = 19;
        } else {
            for (Node<E> value : childen) {
                result = result + value.value.hashCode();
            }
        }
        result = 31 * result + value.hashCode();
        return result;
    }
}
