package ru.nivanov;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 24.04.2017.
 */
public class ArrayTwoDimIteratorTest {
    /**
     * Test for next method.
     */
    @Test
    public void whenNextCorrectThenReturnResult() {
        ArrayTwoDimIterator it = new ArrayTwoDimIterator(new int[][]{{1, 2}, {3, 4}});

        int[] resullt = new int[]{(Integer) it.next(), (Integer) it.next(), (Integer) it.next(), (Integer) it.next()};
        int[] expected = {1, 2, 3, 4};

        assertThat(resullt, is(expected));

    }

    /**
     * Test for hasNext method.
     */
    @Test
    public void whenHasNextCorrectThenReturnResult() {
        ArrayTwoDimIterator it = new ArrayTwoDimIterator(new int[][]{{1, 2}, {3, 4}});

        boolean[] results = new boolean[6];
        results[0] = it.hasNext();
        it.next();
        results[1] = it.hasNext();
        it.next();
        results[2] = it.hasNext();
        it.next();
        results[3] = it.hasNext();
        it.next();
        results[4] = it.hasNext();
        results[5] = it.hasNext();

        boolean[] expected = {true, true, true, true, false, false};

        assertThat(results, is(expected));
    }
}