package ru.nivanov.simpleArrayList;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 02.05.2017.
 */
public class ArrayContainerTest {
    /**
     * Test for add object.
     */
    @Test
    public void whenAddThenReturnResult() {
        ArrayContainer<String> underTest = new ArrayContainer<>(2);
        underTest.add("one");
        underTest.add("two");
        underTest.add("test");

        String[] results = {underTest.get(0), underTest.get(1), underTest.get(2)};
        String[] expected = {"one", "two", "test"};

        assertThat(results, is(expected));

    }

    /**
     * Test for get method.
     */
    @Test
    public void whenGetByIndexThenReturnResult() {
        final int three = 3;
        ArrayContainer<Integer> underTest = new ArrayContainer<>(2);
        underTest.add(1);
        underTest.add(three);

        assertThat(underTest.get(1), is(three));

    }

    /**
     * Test for iterator hasNext.
     */
    @Test
    public void whenHasNextThenReturnResult() {
        ArrayContainer<String> underTest = new ArrayContainer<>(2);
        underTest.add("one");
        underTest.add("two");

        Iterator iter = underTest.iterator();
        boolean one = iter.hasNext();
        String resultOne = (String) iter.next();

        boolean two = iter.hasNext();
        String resultTwo = (String) iter.next();

        boolean three = iter.hasNext();

        boolean[] results = {one, two, three};
        boolean[] expected = {true, true, false};

        assertThat(results, is(expected));

    }

    /**
     * Test for iterator hasNext.
     */
    @Test
    public void whenNextThenReturnResult() {
        ArrayContainer<String> underTest = new ArrayContainer<>(2);
        underTest.add("one");
        underTest.add("two");

        Iterator iter = underTest.iterator();
        boolean one = iter.hasNext();
        String resultOne = (String) iter.next();

        boolean two = iter.hasNext();
        String resultTwo = (String) iter.next();

        String[] results = {resultOne, resultTwo};
        String[] expected = {"one", "two"};

        assertThat(results, is(expected));
    }

}