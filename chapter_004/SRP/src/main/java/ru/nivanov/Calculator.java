package ru.nivanov;

import java.util.ArrayList;

import static jdk.nashorn.internal.objects.Global.Infinity;

/**
 * Created by Nikolay Ivanov on 17.02.2017.
 */
class Calculator implements ICalculator {
    private static final int THREE = 3;
    private double result;

    /**
     * @param first ..
     * @param second ..
     */
    private void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * @param first ..
     * @param second ..
     */
    private void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * @param first ..
     * @param second ..
     */
    private void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * @param first ..
     * @param second ..
     */
    private void div(double first, double second) {
        if (second == 0d) {
            System.out.println("Zero division!");
            this.result = Infinity;
        } else {
            this.result = first / second;
        }
    }

    /**
     * Get result.
     * @return result
     */
    public double getResult() {
        return this.result;
    }

    /**
     * Calculate method.
     * @param mode ..
     * @param args ..
     */
    public void calculate(int mode, ArrayList<Double> args) {
        if (0 == mode) {
            this.add(args.get(0), args.get(1));
        } else if (1 == mode) {
            this.subtract(args.get(0), args.get(1));
        } else if (2 == mode) {
            this.multiple(args.get(0), args.get(1));
        } else if (THREE == mode) {
            this.div(args.get(0), args.get(1));
        } else {
            System.out.println("operation not supported");
        }
    }


}


