package ru.nivanov;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Nikolay Ivanov on 13.02.2017.
 */
public class FindTest {
    private static final int SIZE = 15;

    /**
     * test for show help.
     * @throws Exception ..
     */
    @Test
    public void whenOutputHelpThenReturnResult() throws Exception {

        System.setOut(new PrintStream(new File("test.txt")));
        char[] cbuf = new char[SIZE];
        Find.showTips();
        FileReader expect = new FileReader("test.txt");
        expect.read(cbuf);
        String newString = String.valueOf(cbuf);
        String expected = "Usage: find.jar";
        assertThat(newString, is(expected));
    }

    /**
     * test for validating input.
     * @throws Exception ..
     */
    @Test
    public void whenValidateWorksThenReturnResult() throws Exception {
        Find find = new Find();
        String[] input = {"-d", "1", "-n", "2", "-m", "-o", "3"};
        find.validate(input);
        assertTrue(find.validate(input));
    }
}