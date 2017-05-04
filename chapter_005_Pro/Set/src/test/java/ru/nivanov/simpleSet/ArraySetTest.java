package ru.nivanov.simpleSet;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 04.05.2017.
 */
public class ArraySetTest {

    /**
     * Test for adding and iterating.
     */
    @Test
    public void whenAddThenReturnResult() {
        ArraySet<String> underTest = new ArraySet<>(2);

        underTest.add(null);
        underTest.add("one");
        underTest.add("two");
        underTest.add("three");
        underTest.add("two");

        final int four = 4;
        String[] results = new String[four];
        int i = 0;
        while (underTest.hasNext()) {
            results[i] = underTest.next();
            i++;
        }
        String[] expected = {"one", "two", "three", null};

        assertThat(results, is(expected));

    }


}