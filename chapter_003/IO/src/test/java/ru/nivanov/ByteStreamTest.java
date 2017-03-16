package ru.nivanov;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ByteStreamTest class.
 * @author nivanov.
 */
public class ByteStreamTest {
    /**
     * Test for even number.
     * @throws IOException ..
     */
    @Test
    public void whenEvenNumberCorrectThenReturnOk() throws IOException {
        ByteArrayInputStream arr = new ByteArrayInputStream("13f 28 33 4t".getBytes());
        ByteStream test = new ByteStream();
        boolean result = test.isNumber(arr);
        assertThat(result, is(true));
    }
}