package ru.nivanov.simpleSet;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 04.05.2017.
 */
public class LinkedListSetTest {
    private LinkedListSet<String> underTest;

    /**
     * Setting up.
     */
    public void setUp() {
        underTest = new LinkedListSet<>();
        underTest.add("one");
        underTest.add("two");
        underTest.add("one");
    }

    /**
     * Test for add method.
     */
    @Test
    public void whenAddThenReturnResult() {
        setUp();
        String result = underTest.next();
        assertThat(result, is("one"));
    }

    /**
     * Test for hasNext method.
     */
    @Test
    public void whenHasNextThenReturnResult() {
        setUp();
        boolean beforeOne = underTest.hasNext();
        underTest.next();
        boolean beforeTwo = underTest.hasNext();
        underTest.next();
        boolean falseExpected = underTest.hasNext();

        boolean[] results = {beforeOne, beforeTwo, falseExpected};
        boolean[] expected = {true, true, false};

        assertThat(results, is(expected));

    }

    /**
     * Test for next method.
     * @throws Exception ..
     */
    @Test(expected = NoSuchElementException.class)
    public void whenNextFailsThenThrowException() throws Exception {
        setUp();
        underTest.next();
        underTest.next();
        underTest.next();
    }

}