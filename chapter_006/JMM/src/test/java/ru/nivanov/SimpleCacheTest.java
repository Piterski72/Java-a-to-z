package ru.nivanov;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 16.06.2017.
 */
public class SimpleCacheTest {
    private String sep = System.getProperty("line.separator");

    /**
     * Test for reading file content.
     */
    @Test
    public void whenLoadFileThenReturnContent() {
        SimpleCache underTest = new SimpleCache();
        String result = underTest.loadFile("c:\\java\\temp\\Names.txt");
        String expected = String.format("Ann%sJoshua%sMike%s", sep, sep, sep);
        System.out.println(result);
        String resultCache = underTest.loadFile("c:\\java\\temp\\Names.txt");
        System.out.println(resultCache);

        assertThat(result, is(expected));

    }

}