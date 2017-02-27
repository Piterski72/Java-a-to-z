package ru.nivanov;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 21.02.2017.
 */
public interface ICalculator {

    /**
     * Get result.
     * @return ..
     */
    double getResult();

    /**
     * Calculate method.
     * @param input ..
     * @param args ..
     */
    void calculate(int input, ArrayList<Double> args);
}
