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
    public void getDouble() throws Exception {
        //add io behavior
        when(io.read()).thenReturn("2");
        Validator underTest = new Validator(io);
        double expected = underTest.getDouble("");
        assertThat(expected, is(2d));

    }

    /**
     * Test for getString.
     * @throws Exception ..
     */
    @Test
    public void getString() throws Exception {
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
    public void compare() throws Exception {
        //add io behavior
        when(io.read()).thenReturn("y");
        Validator underTest = new Validator(io);
        boolean test = true;
        boolean expected = underTest.compare("", "y");
        assertThat(test, is(expected));
    }
}