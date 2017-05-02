package ru.nivanov;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 27.04.2017.
 */
public class SimpleArrayTest {
    private final int five = 5;
    private final int six = 6;

    /**
     * Test for adding string objects.
     */
    @Test
    public void whenAddStringThenReturnResult() {
        SimpleArray<String> underTest = new SimpleArray<>(new String[2]);
        underTest.add("test");
        String result = underTest.get(0);
        assertThat(result, is("test"));
    }

    /**
     * Test for adding integer objects.
     */
    @Test
    public void whenAddIntThenReturnResult() {
        SimpleArray<Integer> underTest = new SimpleArray<>(new Integer[2]);
        underTest.add(2);
        int result = underTest.get(0);
        assertThat(result, is(2));
    }

    /**
     * Test for delete integer object.
     */
    @Test
    public void whenDeleteIntegerObjectThenReturnResult() {
        final int three = 3;
        SimpleArray<Integer> underTest = new SimpleArray<>(new Integer[five]);
        for (int i = 1; i < six; i++) {
            underTest.add(i);
        }
        underTest.delete(three);
        Object[] result = underTest.getObjects();
        Integer[] expected = {1, 2, three, five, null};
        assertThat(result, is(expected));
    }

    /**
     * Test for delete string object.
     */
    @Test
    public void whenDeleteStringObjectThenReturnResult() {
        SimpleArray<String> underTest = new SimpleArray<>(new String[five]);
        for (int i = 1; i < six; i++) {
            underTest.add("test" + i);
        }
        underTest.delete(2);
        Object[] result = underTest.getObjects();
        String[] expected = {"test1", "test2", "test4", "test5", null};
        assertThat(result, is(expected));
    }

    /**
     * Test for updating values.
     */
    @Test
    public void whenUpdateThenReturnResult() {
        SimpleArray<String> underTest = new SimpleArray<>(new String[2]);
        for (int i = 1; i < 2; i++) {
            underTest.add("test" + i);
        }
        underTest.update("updated", 1);
        assertThat(underTest.get(1), is("updated"));
    }
}