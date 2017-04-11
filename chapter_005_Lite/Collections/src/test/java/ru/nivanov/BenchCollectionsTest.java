package ru.nivanov;

import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeSet;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 11.04.2017.
 */
public class BenchCollectionsTest {
    private final int count = 3;
    private final BenchCollections underTest = new BenchCollections();
    private final String test = "test";
    private final ArrayList<String> arrayList = new ArrayList<>();

    /**
     * Test for add method.
     */
    @Test
    public void whenAddThenReturnResult() {
        underTest.add(arrayList, test, count);
        final int three = 3;
        assertThat(arrayList.size(), is(three));
    }

    /**
     * Test for remove method.
     */
    @Test
    public void whenDeleteThenReturnResult() {
        TreeSet<String> treeSet = new TreeSet<>();
        underTest.add(treeSet, test, count);
        underTest.delete(treeSet, 2);
        assertThat(treeSet.size(), is(1));
    }

    /**
     *
     */
    @Test
    public void whenGenerateStringThenReturnResult() {
        String[] test = underTest.stringGenerator("test", count);
        int result = test.length;
        final int expected = 3;
        assertThat(result, is(expected));

    }

}