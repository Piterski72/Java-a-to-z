package ru.nivanov;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 20.05.2017.
 */
public class TreeTest {
    /**
     * Test for add and iterate.
     */
    @Test
    public void whenAddToTreeThenReturnResult() {

        Node<String> parent = new Node<>("A");

        Tree<String> underTest = new Tree<>(parent);

        Node<String> childOne = new Node<>("B");
        Node<String> childTwo = new Node<>("C");
        Node<String> childThree = new Node<>("D");

        underTest.add(parent, childOne);
        underTest.add(parent, childTwo);
        underTest.add(parent, childThree);

        underTest.add(childOne, new Node<>("E"));
        underTest.add(childOne, new Node<>("F"));
        underTest.add(childTwo, new Node<>("G"));
        underTest.add(childThree, new Node<>("H"));

        Iterator<Node<String>> iter = underTest.iterator();

        String result = "";
        while (iter.hasNext()) {
            result = result + iter.next().value;

        }
        assertThat(result, is("EFBGCHDA"));

    }

    @Test
    public void iterator() throws Exception {

    }

    @Test
    public void compare() throws Exception {

    }

}