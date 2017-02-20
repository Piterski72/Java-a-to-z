package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 17.02.2017.
 */
class Calculator {
    private double result;

    /**
     * summ.
     * @param first ..
     * @param second ..
     */
    void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * subtract.
     * @param first ..
     * @param second ..
     */
    void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * multiple.
     * @param first ..
     * @param second ..
     */
    void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * division.
     * @param first ..
     * @param second ..
     */
    void div(double first, double second) {
        if (second == 0d) {
            System.out.println("Zero division!");
            this.result = 0;
        } else {
            this.result = first / second;
        }
    }

    /**
     * Get result.
     * @return result
     */
    double getResult() {
        return this.result;
    }

    /**
     * Calculate method.
     * @param input ..
     * @param first ..
     * @param second ..
     */
    void calculate(String input, double first, double second) {
        if ("+".equals(input)) {
            this.add(first, second);
        } else if ("-".equals(input)) {
            this.subtract(first, second);
        } else if ("*".equals(input)) {
            this.multiple(first, second);
        } else if ("/".equals(input)) {
            this.div(first, second);
        } else {
            throw new UnsupportedOperationException();
        }
    }
}


