package ru.nivanov.SuperIterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 27.04.2017.
 */
public class IteratorsConverterTest {

    /**
     * Test for converting values.
     */
    @Test
    public void whenConvertSuccesThenReturnResult() {
        final int three = 3;
        final int four = 4;
        final int five = 5;
        final int six = 6;
        final int seven = 7;
        final int eight = 8;
        final int nine = 9;


        ArrayList<Integer> oneList = new ArrayList<>(Arrays.asList(four, 2, 0, four, six, four, nine));
        ArrayList<Integer> twoList = new ArrayList<>(Arrays.asList(0, nine, eight, seven, five));
        ArrayList<Integer> threeList = new ArrayList<>(Arrays.asList(1, three, five, six, seven, 0, nine, eight, four));

        ArrayList<Iterator<Integer>> iteratorsList = new ArrayList<>(
                Arrays.asList(oneList.iterator(), twoList.iterator(), threeList.iterator()));

        IteratorsConverter underTest = new IteratorsConverter();
        Iterator<Integer> resultIterator = underTest.convert(iteratorsList.iterator());

        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> expected = new ArrayList<>(
                Arrays.asList(four, 2, 0, four, six, four, nine, 0, nine, eight, seven, five, 1, three, five, six,
                        seven, 0, nine, eight, four));

        while (resultIterator.hasNext()) {
            result.add(resultIterator.next());
        }

        assertThat(result, is(expected));
    }

    /**
     * Test for converting one value.
     */
    @Test
    public void whenConvertOneValueThenReturnResult() {

        ArrayList<Integer> oneList = new ArrayList<>(Collections.singletonList(1));

        ArrayList<Iterator<Integer>> iteratorsList = new ArrayList<>(Collections.singletonList(oneList.iterator()));

        IteratorsConverter underTest = new IteratorsConverter();
        Iterator<Integer> resultIterator = underTest.convert(iteratorsList.iterator());

        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> expected = new ArrayList<>(Collections.singletonList(1));

        while (resultIterator.hasNext()) {
            result.add(resultIterator.next());
        }

        assertThat(result, is(expected));
    }


}