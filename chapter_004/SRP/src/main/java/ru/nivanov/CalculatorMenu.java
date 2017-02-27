package ru.nivanov;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 20.02.2017.
 */
class CalculatorMenu {
    private static final String SEP = System.getProperty("line.separator");
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private final IO io;
    private ICalculator calculator;
    private ArrayList<CalcActions> actions = new ArrayList<>();

    /**
     * Constructor.
     * @param io ..
     */
    CalculatorMenu(ICalculator calculator, IO io) {
        this.calculator = calculator;
        this.io = io;
    }

    /**
     * Get range.
     * @return ..
     */
    int[] getRange() {
        int[] menuRange = new int[actions.size()];
        for (int i = 0; i < actions.size(); i++) {
            menuRange[i] = i;
        }
        return menuRange;
    }

    /**
     * Get actions.
     * @return ..
     */
    ArrayList<CalcActions> getActions() {
        return this.actions;
    }

    /**
     * filing actions.
     */
    void fillActions() {
        actions.add(new BasicOperation(0, "Add operation"));
        actions.add(new BasicOperation(1, "Subtract operation"));
        actions.add(new BasicOperation(2, "Multiple operation"));
        actions.add(new BasicOperation(THREE, "Divide operation"));
        actions.add(new ExtOperation(FOUR, "Sinus calculate"));
        actions.add(new ExtOperation(FIVE, "Cosinus calculate"));
        actions.add(new ExtOperation(SIX, "Tangens calculate"));
        actions.add(new ExtOperation(SEVEN, "Cotangens calculate"));
    }

    /**
     * Show main menu.
     */
    void showMenu() {
        for (CalcActions action : actions) {
            this.io.println(action.key() + "." + action.info());
        }
    }

    /**
     * Menu selection method.
     * @param validator ..
     * @param reuse ..
     */
    void selectMenu(Validator validator, boolean reuse) {
        int input = validator.getInt("Enter operation");
        boolean check = validator.checkRange(input, getRange());
        if (check) {
            actions.get(input).execute(validator, reuse);
        } else {
            io.println("Out of menu range");
        }
    }

    /**
     * Class for Basic operation.
     */
    public class BasicOperation implements CalcActions {
        private String id;
        private int key;

        /**
         * Constructor.
         * @param key ..
         * @param id ..
         */
        BasicOperation(int key, String id) {
            this.id = id;
            this.key = key;
        }

        /**
         * Returns op. info.
         * @return ..
         */
        public String info() {
            return this.id;
        }

        /**
         * Returns number key  of op.
         * @return ..
         */
        public int key() {
            return this.key;
        }

        /**
         * Execute action.
         * @param validator ..
         * @param reuse ..
         */
        public void execute(Validator validator, boolean reuse) {
            ArrayList<Double> vals = new ArrayList<>();
            if (reuse) {
                vals.add(calculator.getResult());
                io.println(String.format("first arg is: %s", vals.get(0)));
            } else {
                vals.add(validator.getDouble("Enter first arg"));
            }
            vals.add(validator.getDouble("Enter second arg"));
            calculator.calculate(this.key(), vals);
        }
    }

    /**
     * Class for extended operations.
     */
    public class ExtOperation implements CalcActions {
        private String id;
        private int key;

        /**
         * Constructor.
         * @param key ..
         * @param id ..
         */
        ExtOperation(int key, String id) {
            this.id = id;
            this.key = key;
        }

        /**
         * Returns op. info.
         * @return ..
         */
        public String info() {
            return this.id;
        }

        /**
         * Returns number key  of op.
         * @return ..
         */
        public int key() {
            return this.key;
        }

        /**
         * Execute action.
         * @param validator ..
         * @param reuse ..
         */
        public void execute(Validator validator, boolean reuse) {
            ArrayList<Double> vals = new ArrayList<>();
            if (reuse) {
                vals.add(calculator.getResult());
                io.println(String.format("arg is: %s", vals.get(0)));
            } else {
                vals.add(validator.getDouble("Enter arg"));
            }
            calculator.calculate(this.key(), vals);
        }
    }
}
