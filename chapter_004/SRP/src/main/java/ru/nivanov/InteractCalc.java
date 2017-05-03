package ru.nivanov;

import java.util.Scanner;

/**
 * Created by Nikolay Ivanov on 17.02.2017.
 */
class InteractCalc {
    private final ICalculator calculator;
    private final IO io;

    /**
     * Constroctor.
     * @param calculator ..
     * @param io ..
     */
    private InteractCalc(final ICalculator calculator, final IO io) {
        this.calculator = calculator;
        this.io = io;
    }

    /**
     * Main method.
     * @param args ..
     */
    public static void main(String[] args) {
        ICalculator calc = new EngineerCalc();
        new InteractCalc(calc, new ConsoleIO(new Scanner(System.in), System.out)).start();
    }

    /**
     * start method.
     */
    private void start() {
        CalculatorMenu calcMenu = new CalculatorMenu(calculator, new ConsoleIO(new Scanner(System.in), System.out));
        calcMenu.fillActions();
        boolean reuse = false;
        try {
            final Validator validator = new Validator(io);
            do {

                calcMenu.showMenu();
                calcMenu.selectMenu(validator, reuse);
                io.println(String.format("The result is: %s", this.calculator.getResult()));
                reuse = validator.compare("Do you want to reuse result? (y)", "y");
            } while (validator.compare("Do you want to continue? (y)", "y"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
