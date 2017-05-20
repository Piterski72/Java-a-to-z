package ru.nivanov;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nikolay Ivanov on 18.05.2017.
 */
public class Node<E> {

    List<Node<E>> childen;
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

        if (!childen.equals(node.childen)) return false;
        return value.equals(node.value);
    }

    @Override
    public int hashCode() {
        int result = childen.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
