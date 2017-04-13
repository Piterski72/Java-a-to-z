package ru.nivanov.start;

/**
 * StartUI class.
 * @author nivanov.
 */

class StartUI {
    private final Input input;
    private final Tracker tracker;

    StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * main method.
     * @param args input param
     */
    public static void main(String[] args) {
        Input input = new ValidateInput();
        Tracker tracker = new Tracker();
        new StartUI(input, tracker).init();
    }

    /**
     * realization.
     * ..
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        do {
            menu.show();
            menu.select(input.ask("Select: ", menu.getRange()));
        } while (!"y".equals(this.input.ask("Exit? (y) ")));
    }
}