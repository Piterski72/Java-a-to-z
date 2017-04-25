package ru.nivanov;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 24.04.2017.
 */
public class ArrayTwoDimIteratorTest {
    private final int three = 3;
    private final int four = 4;

    /**
     * Test for next method.
     */
    @Test
    public void whenNextCorrectThenReturnResult() {
        ArrayTwoDimIterator it = new ArrayTwoDimIterator(new int[][]{{1, 2}, {three, four}});

        int[] resullt = new int[]{(Integer) it.next(), (Integer) it.next(), (Integer) it.next(), (Integer) it.next()};
        int[] expected = {1, 2, three, four};

        assertThat(resullt, is(expected));

    }

    /**
     * Test for hasNext method.
     */
    @Test
    public void whenHasNextCorrectThenReturnResult() {
        ArrayTwoDimIterator it = new ArrayTwoDimIterator(new int[][]{{1, 2}, {three, four}});

        final int six = 6;
        boolean[] results = new boolean[six];
        results[0] = it.hasNext();
        it.next();
        results[1] = it.hasNext();
        it.next();
        results[2] = it.hasNext();
        it.next();
        results[three] = it.hasNext();
        it.next();
        results[four] = it.hasNext();
        final int five = 5;
        results[five] = it.hasNext();

        boolean[] expected = {true, true, true, true, false, false};

        assertThat(results, is(expected));
    }
}