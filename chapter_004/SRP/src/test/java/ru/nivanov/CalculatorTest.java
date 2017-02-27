package ru.nivanov;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 20.02.2017.
 */
public class CalculatorTest {
    private final int three = 3;
    private final double four = 4;
    private Calculator underTest = new Calculator();
    private ArrayList<Double> list = new ArrayList<>();

    /**
     * Test for calculate operation.
     * @throws Exception ..
     */
    @Test
    public void whenCalculateThenReturnResult() throws Exception {
        list.add((double) 2);
        list.add((double) 2);
        underTest.calculate(0, list);
        double res1 = underTest.getResult();
        underTest.calculate(1, list);
        double res2 = underTest.getResult();
        underTest.calculate(2, list);
        double res3 = underTest.getResult();
        underTest.calculate(three, list);
        double res4 = underTest.getResult();
        double[] expected = {four, 0, four, 1};
        double[] results = {res1, res2, res3, res4};
        assertThat(results, is(expected));
    }
}