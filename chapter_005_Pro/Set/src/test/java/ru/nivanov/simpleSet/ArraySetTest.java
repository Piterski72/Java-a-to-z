package ru.nivanov.simpleSet;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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

    /**
     * Test for adding and iterating.
     */
    @Test
    public void whenAddFastThenReturnResult() {
        ArraySet<String> underTest = new ArraySet<>(2);

        underTest.addFast("one");
        underTest.addFast("two");
        underTest.addFast("three");
        underTest.addFast("two");
        underTest.addFast("aaa");
        underTest.addFast("zzz");

        final int five = 5;
        String[] results = new String[five];
        int i = 0;
        while (underTest.hasNext()) {
            results[i] = underTest.next();
            i++;
        }
        String[] expected = {"aaa", "one", "three", "two", "zzz"};

        assertThat(results, is(expected));

    }

    /**
     * Test for comparing speed of adding elements in collection by add and addFast methods.
     */
    @Test
    public void whenCompareAddItemsTimeThenReturnResult() {
        final int testCount = 10000;
        ArraySet<Integer> underTestOne = new ArraySet<>();
        ArraySet<Integer> underTestTwo = new ArraySet<>();
        long startOne = System.currentTimeMillis();
        for (int i = 0; i < testCount; i++) {
            underTestOne.add(i);
        }
        long endOne = System.currentTimeMillis();
        long resultOne = endOne - startOne;

        System.out.println(String.format("total time for add method is %d", resultOne));

        long startTwo = System.currentTimeMillis();
        for (int i = testCount; i > 0; i--) {
            underTestOne.addFast(i);
        }
        long endTwo = System.currentTimeMillis();
        long resultTwo = endTwo - startTwo;
        System.out.println(String.format("total time for addFast method is %d", resultTwo));

        assertTrue(resultTwo < resultOne);

    }
}