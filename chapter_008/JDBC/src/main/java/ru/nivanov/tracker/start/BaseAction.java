package ru.nivanov.tracker.start;

/**
 * BaseAction abstract class.
 * @author nivanov.
 */
public abstract class BaseAction implements UserAction {
    private final String name;

    public BaseAction(String name) {
        this.name = name;
    }

    /**
     * Menu selection (abstract).
     * @return key..
     */
    public abstract int key();

    /**
     * Menu acton (abstract.
     * @param input ..
     * @param tracker ..
     */
    public abstract void execute(Input input, Tracker tracker);

    /**
     * Info method.
     * @return info..
     */
    public String info() {
        return String.format("%d. %s ", this.key(), this.name);
    }
}