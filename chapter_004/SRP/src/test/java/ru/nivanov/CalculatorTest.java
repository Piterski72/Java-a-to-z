package ru.nivanov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 20.02.2017.
 */
public class CalculatorTest {
    private final double four = 4;
    private Calculator underTest = new Calculator();

    /**
     * Test for calculate operation.
     * @throws Exception ..
     */
    @Test
    public void whenCalculateThenReturnResult() throws Exception {
        underTest.calculate("+", 2d, 2d);
        double res1 = underTest.getResult();
        underTest.calculate("-", four, 2d);
        double res2 = underTest.getResult();
        underTest.calculate("*", 2d, 2d);
        double res3 = underTest.getResult();
        underTest.calculate("/", 2d, 2d);
        double res4 = underTest.getResult();
        double[] expected = {four, 2, four, 1};
        double[] results = {res1, res2, res3, res4};
        assertThat(results, is(expected));
    }
}