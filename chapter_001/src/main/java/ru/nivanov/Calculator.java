package ru.nivanov;

/**
 * Creating calculator.
 * @author nivanov.
 */

public class Calculator {
    private double result;

    /**
     * summ.
     * @param first ..
     * @param second ..
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * subtract.
     * @param first ..
     * @param second ..
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * multiple.
     * @param first ..
     * @param second ..
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * division.
     * @param first ..
     * @param second ..
     */
    public void div(double first, double second) {
        if (second == 0d) {
            System.out.println("Zero division!");
        } else {
            this.result = first / second;
        }
    }

    public double getResult() {
        return this.result;
    }
}