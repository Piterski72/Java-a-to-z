package ru.nivanov;

import java.util.Scanner;

/**
 * Created by Nikolay Ivanov on 17.02.2017.
 */
class InteractCalc {
    private final Calculator calculator;
    private final IO io;

    /**
     * Constroctor.
     * @param calculator ..
     * @param io ..
     */
    private InteractCalc(final Calculator calculator, final IO io) {
        this.calculator = calculator;
        this.io = io;
    }

    /**
     * Main method.
     * @param args ..
     */
    public static void main(String[] args) {
        //new CalculatorMenu(new ConsoleIO(new Scanner(System.in), System.out)).showCalcResultMenu();
        new InteractCalc(new Calculator(), new ConsoleIO(new Scanner(System.in), System.out)).start();
    }

    /**
     * Start method.
     */
    public void start() {
        boolean reuse = false;
        try {
            final Validator validator = new Validator(io);
            do {
                final double first;
                if (reuse) {
                    first = calculator.getResult();
                } else {
                    first = validator.getDouble("enter first arg");
                }
                String operation = validator.getString("Enter operation");
                double second = validator.getDouble("enter second arg");
                calculator.calculate(operation, first, second);
                io.println(String.format("%s %s %s = %s", first, operation, second, calculator.getResult()));
                reuse = validator.compare("Do you want to reuse result? (y)", "y");
            } while (validator.compare("Do you want to continue? (y)", "y"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
