package ru.nivanov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test of calculating factorial.
 * @author nivanov.
 */

public class FactorialTest {
    /**
     * Test of factorial of 6.
     */
    @Test
    public void whenCalculateFactThenReturnIt() {
        final int one = 6;
        final int expected = 720;
        Factorial factorial = new Factorial(one);
        int z = factorial.calcF();
        assertThat(z, is(expected));
    }
}