package ru.nivanov;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 27.02.2017.
 */
public class EngineerCalcTest {
    private final int four = 4;
    private final int five = 5;
    private final int six = 6;
    private final int seven = 7;
    private final int ninety = 90;
    private final int fortyFive = 45;
    private final int sto35 = 135;
    private ICalculator underTestEng = new EngineerCalc();
    private ArrayList<Double> list = new ArrayList<>();

    /**
     * Test for calculate operation.
     * @throws Exception ..
     */
    @Test
    public void whenCalculateExtThenReturnResult() throws Exception {
        list.add((double) ninety);
        underTestEng.calculate(four, list);
        double res1 = Math.round(underTestEng.getResult());
        list.remove(0);
        list.add((double) 0);
        underTestEng.calculate(five, list);
        double res2 = Math.round(underTestEng.getResult());
        list.remove(0);
        list.add((double) fortyFive);
        underTestEng.calculate(six, list);
        double res3 = Math.round(underTestEng.getResult());
        list.remove(0);
        list.add((double) sto35);
        underTestEng.calculate(seven, list);
        double res4 = Math.round(underTestEng.getResult());
        double[] expected = {1, 1, 1, -1};
        double[] results = {res1, res2, res3, res4};
        assertThat(results, is(expected));

    }


}