package ru.nivanov.start;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ConsoleInputTest class.
 * @author nivanov.
 */

public class ConsoleInputTest {
    private final int three = 3;
    /**
     * Test for console input.
     */
    @Test
    public void whenInput123ThenReturnOk() {
        System.setIn(new ByteArrayInputStream("123".getBytes()));
        ConsoleInput test = new ConsoleInput();
        assertThat(test.ask("enter 123\n"), is("123"));
    }

    /**
     * Test for exception.
     */
    @Test
    public void whenNotExceptionThenReturnInput() {
        System.setIn(new ByteArrayInputStream("2".getBytes()));
        ConsoleInput test = new ConsoleInput();
        ArrayList<Integer> row = new ArrayList<>();
        row.addAll(Arrays.asList(1, 2, three));
        int expect = test.ask("Enter number: ", row);
        assertThat(expect, is(2));
    }

    /**
     * Test for exception2.
     */
    @Test
    public void whenExceptionTrueThenReturnMOE() {
        System.setIn(new ByteArrayInputStream("4".getBytes()));
        ConsoleInput test = new ConsoleInput();
        ArrayList<Integer> row = new ArrayList<>();
        row.addAll(Arrays.asList(1, 2, three));
        boolean err = false;
        try {
            test.ask("Enter number: ", row);
        } catch (MenuOutException moe) {
            err = true;
        }
        assertThat(err, is(true));
    }
}