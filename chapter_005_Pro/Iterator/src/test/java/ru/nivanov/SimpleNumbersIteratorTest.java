package ru.nivanov;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 25.04.2017.
 */
public class SimpleNumbersIteratorTest {
    private final int three = 3;
    private final int four = 4;
    private final int six = 6;

    /**
     * Test for hasNext.
     */
    @Test
    public void whenHasNextThenReturnResult() {
        final int nine = 9;
        SimpleNumbersIterator snit = new SimpleNumbersIterator(new int[]{1, three, six, four, nine});
        boolean one = snit.hasNext();
        snit.next();
        snit.next();
        boolean two = snit.hasNext();
        boolean[] results = {one, two};
        boolean[] expected = {true, false};
        assertThat(results, is(expected));

    }

    /**
     * Test for next method.
     */
    @Test
    public void whenNextThenReturnResult() {
        final int five = 5;
        final int seven = 7;
        SimpleNumbersIterator snit = new SimpleNumbersIterator(new int[]{1, 2, three, four, five, six, seven});
        int[] result = new int[]{(Integer) snit.next(),
                                 (Integer) snit.next(),
                                 (Integer) snit.next(), (Integer) snit.next(), (Integer) snit.next(),};
        int[] expected = new int[]{1, 2, three, five, seven};
        assertThat(result, is(expected));

    }

}