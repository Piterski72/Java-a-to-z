package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 19.07.2017.
 */
public class Model {

    private String name;
    private volatile int version = 0;

    /**
     * Constructor.
     * @param name ..
     */
    public Model(String name) {
        this.name = name;
    }

    /**
     * Name getter.
     * @return ..
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter.
     * @param name ..
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get version.
     * @return current version.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Increment version.
     */
    public void incrementVersion() {
        this.version++;
    }
}
