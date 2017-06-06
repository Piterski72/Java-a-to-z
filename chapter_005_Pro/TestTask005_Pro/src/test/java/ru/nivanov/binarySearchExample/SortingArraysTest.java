package ru.nivanov.binarySearchExample;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 06.06.2017.
 */
public class SortingArraysTest {
    /**
     * Test for sorting method.
     */
    @Test
    public void whenSortingThenReturnResult() {
        int[] sorted = {4, 5, 7, 9, 11};
        int[] unsorted = {12, 4, 2, 7, 8, 1};
        SortingArrays underTest = new SortingArrays(sorted, unsorted);
        int[] result = underTest.sorting();
        int[] expected = {1, 2, 4, 4, 5, 7, 7, 8, 9, 11, 12};
        assertThat(result, is(expected));

    }

}