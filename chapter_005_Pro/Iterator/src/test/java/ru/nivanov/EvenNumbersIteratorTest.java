package ru.nivanov;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 24.04.2017.
 */
public class EvenNumbersIteratorTest {
    private final int three = 3;
    private final int six = 6;
    private final int eight = 8;

    /**
     * Test for hasNext.
     */
    @Test
    public void whenHasNextCorrectThenReturnResult() {
        EvenNumbersIterator enit = new EvenNumbersIterator(new int[]{six, eight, three});

        boolean one = enit.hasNext();
        enit.next();
        boolean two = enit.hasNext();
        enit.next();
        boolean three = enit.hasNext();
        enit.next();
        boolean four = enit.hasNext();

        boolean[] results = {one, two, three, four};
        boolean[] expected = {true, true, true, false};

        assertThat(results, is(expected));

    }

    /**
     * Test for next.
     */
    @Test
    public void whenNextCorrectThenReturnResult() {
        final int four = 4;
        final int five = 5;
        EvenNumbersIterator enit = new EvenNumbersIterator(new int[]{six, eight, three, four, five, 2});

        int[] results = new int[]{(Integer) enit.next(),
                                  (Integer) enit.next(),
                                  (Integer) enit.next(),
                                  (Integer) enit.next()};
        int[] expected = new int[]{six, eight, four, 2};

        assertThat(results, is(expected));

    }

}