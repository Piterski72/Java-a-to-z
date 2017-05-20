package ru.nivanov;

import org.junit.Test;

import java.util.Iterator;

/**
 * Created by Nikolay Ivanov on 20.05.2017.
 */
public class TreeTest {
    @Test
    public void whenAddToTreeThenReturnResult() {

        Node<Integer> parent = new Node<>(0);

        Tree<Integer> underTest = new Tree<>(parent);

        Node<Integer> childOne = new Node<>(1);
        Node<Integer> childTwo = new Node<>(2);
        Node<Integer> childThree = new Node<>(3);

        underTest.add(parent, childOne);
        underTest.add(parent, childTwo);
        underTest.add(parent, childThree);


        underTest.add(childOne, new Node<>(4));
        underTest.add(childOne, new Node<>(5));
        underTest.add(childOne, new Node<>(6));


        Iterator<Node<Integer>> iter = underTest.iterator();

        boolean result = iter.hasNext();
        System.out.println(result);

        System.out.println(iter.next().value);
        System.out.println(iter.next().value);
        System.out.println(iter.next().value);
        System.out.println(iter.hasNext());
        System.out.println(iter.next().value);


    }

    @Test
    public void iterator() throws Exception {

    }

    @Test
    public void compare() throws Exception {

    }

}