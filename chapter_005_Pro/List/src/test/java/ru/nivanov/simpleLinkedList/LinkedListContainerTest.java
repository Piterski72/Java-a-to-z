package ru.nivanov.simpleLinkedList;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 03.05.2017.
 */
public class LinkedListContainerTest {

    /**
     * Test for adding and getting objects.
     */
    @Test
    public void whenAddAndGetThenReturnResult() {
        LinkedListContainer<String> underTest = new LinkedListContainer<>();
        underTest.add("test0");
        underTest.add("test1");
        String[] results = {underTest.get(0), underTest.get(1)};
        String[] expected = {"test0", "test1"};
        assertThat(results, is(expected));

    }

    /**
     * Test for iterator.
     */
    @Test
    public void whenIterateThenReturnResult() {
        LinkedListContainer<String> underTest = new LinkedListContainer<>();
        underTest.add("test0");
        underTest.add("test1");

        Iterator<String> iter = underTest.iterator();
        boolean beforeZero = iter.hasNext();
        iter.next();
        boolean beforeOne = iter.hasNext();
        iter.next();
        boolean waitingFalse = iter.hasNext();

        boolean[] results = {beforeZero, beforeOne, waitingFalse};
        boolean[] expected = {true, true, false};

        assertThat(results, is(expected));

    }

}