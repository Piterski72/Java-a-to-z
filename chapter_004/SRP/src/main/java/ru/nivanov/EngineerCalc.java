package ru.nivanov;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 21.02.2017.
 */
public class EngineerCalc extends Calculator {
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private double result;

    /**
     * Get result.
     * @return ..
     */
    public double getResult() {
        return result;
    }

    /**
     * Sinus calculation.
     * @param first ..
     */
    private void sinus(double first) {
        result = Math.sin(Math.toRadians(first));
    }

    /**
     * Cosinus calculation.
     * @param first ..
     */
    private void cosinus(double first) {
        result = Math.cos(Math.toRadians(first));
    }

    /**
     * Tangent calculation.
     * @param first ..
     */
    private void tangens(double first) {
        result = Math.tan(Math.toRadians(first));
    }

    /**
     * Cotangent calculation.
     * @param first ..
     */
    private void cotangens(double first) {
        result = 1.0 / Math.tan(Math.toRadians(first));
    }

    /**
     * Calculate method.
     * @param input ..
     * @param args ..
     */
    public void calculate(int input, ArrayList<Double> args) {
        if (FOUR == input) {
            this.sinus(args.get(0));
        } else if (FIVE == input) {
            this.cosinus(args.get(0));
        } else if (SIX == input) {
            this.tangens(args.get(0));
        } else if (SEVEN == input) {
            this.cotangens(args.get(0));
        } else {
            super.calculate(input, args);
            result = super.getResult();
        }
    }
}
