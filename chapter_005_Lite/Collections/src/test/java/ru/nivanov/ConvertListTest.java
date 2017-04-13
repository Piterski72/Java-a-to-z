package ru.nivanov;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 12.04.2017.
 */
public class ConvertListTest {
    private final ConvertList underTest = new ConvertList();
    private final int three = 3;
    private final int four = 4;
    private final int five = 5;
    private final int six = 6;
    private final int seven = 7;

    /**
     * Test for array to list.
     */
    @Test
    public void whenConvertToListThenReturnResult() {
        final int eight = 8;
        final int nine = 9;
        int[][] array = {{1, 2, three}, {four, five, six}, {seven, eight, nine}};
        List<Integer> result = underTest.toList(array);
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < (array.length * array[0].length); i++) {
            expected.add(i, i + 1);
        }
        assertThat(result, is(expected));

    }

    /**
     * Test for list to array 1.
     */
    @Test
    public void whenConvertToArrayThenReturnResult() {
        List<Integer> listOne = new ArrayList<>();
        int i = 0;
        while (i < seven) {
            listOne.add(i, i + 1);
            i++;
        }
        int[][] result = underTest.toArray(listOne, three);
        int[][] expected = {{1, 2, three}, {four, five, six}, {seven, 0, 0}};
        assertThat(result, is(expected));
    }

    /**
     * Test for list to array 2.
     */
    @Test
    public void whenConvertToArray2ThenReturnResult() {
        List<Integer> listTwo = new ArrayList<>();
        int i = 0;
        while (i < six) {
            listTwo.add(i, i + 1);
            i++;
        }
        int[][] result = underTest.toArray(listTwo, three);
        int[][] expected = {{1, 2}, {three, four}, {five, six}};
        assertThat(result, is(expected));
    }

    /**
     * Test for list to array 3.
     */
    @Test
    public void whenConvertToArray3ThenReturnResult() {
        List<Integer> listTwo = new ArrayList<>();
        int i = 0;
        while (i < three) {
            listTwo.add(i, i + 1);
            i++;
        }
        int[][] result = underTest.toArray(listTwo, three);
        int[][] expected = {{1, 2, three}, {0, 0, 0}, {0, 0, 0}};
        assertThat(result, is(expected));
    }

    /**
     * Test for list to array 4.
     */
    @Test
    public void whenConvertToArray4ThenReturnResult() {
        List<Integer> listTwo = new ArrayList<>();
        int i = 0;
        while (i < 2) {
            listTwo.add(i, i + 1);
            i++;
        }
        int[][] result = underTest.toArray(listTwo, three);
        int[][] expected = {{1, 2}, {0, 0}, {0, 0}};
        assertThat(result, is(expected));
    }

    /**
     * Test for list to another list.
     */
    @Test
    public void whenConvertToGlobalListThenReturnResult() {
        List<int[]> listOfMassInts = new ArrayList<>();
        listOfMassInts.add(new int[]{1, 2});
        listOfMassInts.add(new int[]{three, four, five, six});

        List<Integer> result = underTest.convert(listOfMassInts);

        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < six; i++) {
            expected.add(i, i + 1);
        }
        assertThat(result, is(expected));
    }

}