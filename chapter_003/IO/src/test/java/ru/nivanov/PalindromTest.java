package ru.nivanov;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * PalindromTest class.
 * @author nivanov.
 */
public class PalindromTest {
    /**
     * Test for palindrom existence.
     * @throws IOException ..
     */
    @Test
    public void whenPalikCorrectThenReturnOk() throws IOException {
        BufferedReader arr = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("kOmoK".getBytes())));
        Palindrom test = new Palindrom();
        boolean result = test.isPalindrom(arr.readLine());
        assertThat(result, is(true));
    }
}