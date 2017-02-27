package ru.nivanov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Nikolay Ivanov on 20.02.2017.
 */
public class ValidatorTest {
    private IO io = mock(IO.class);

    /**
     * Test for getDouble method.
     * @throws Exception ..
     */
    @Test
    public void whenGetDoubleThenReturnResult() throws Exception {
        //add io behavior
        when(io.read()).thenReturn("2");
        Validator underTest = new Validator(io);
        double expected = underTest.getDouble("");
        assertThat(expected, is(2d));

    }

    /**
     * Test for getInt.
     * @throws Exception ..
     */
    @Test
    public void whenGetIntThenReturnResult() throws Exception {
        //add io behavior
        when(io.read()).thenReturn("1");
        Validator underTest = new Validator(io);
        int expected = underTest.getInt("");
        assertThat(expected, is(1));

    }

    /**
     * Test for getString.
     * @throws Exception ..
     */
    @Test
    public void whenGetStringThenReturnResult() throws Exception {
        //add io behavior
        when(io.read()).thenReturn("test");
        Validator underTest = new Validator(io);
        String expected = underTest.getString("");
        assertThat(expected, is("test"));

    }

    /**
     * Test for compare method.
     * @throws Exception ..
     */
    @Test
    public void whenCompareThenReturnResult() throws Exception {
        //add io behavior
        when(io.read()).thenReturn("y");
        Validator underTest = new Validator(io);
        boolean test = true;
        boolean expected = underTest.compare("", "y");
        assertThat(test, is(expected));
    }

    /**
     * Test for check Range method.
     * @throws Exception ..
     */
    @Test
    public void whenChechRangeThenReturnResult() throws Exception {
        final int three = 3;
        final int four = 4;
        boolean[] results = new boolean[2];
        boolean[] expected = {true, false};
        final int inRange = 3;
        final int notInRange = 5;
        int[] testRange = {0, 1, 2, three, four};
        Validator underTest = new Validator(io);
        results[0] = underTest.checkRange(inRange, testRange);
        results[1] = underTest.checkRange(notInRange, testRange);
        assertThat(expected, is(results));
    }
}