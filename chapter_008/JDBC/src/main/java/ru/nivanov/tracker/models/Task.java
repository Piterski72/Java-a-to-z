package ru.nivanov.tracker.models;

/**
 * Task class.
 * @author nivanov.
 */

public class Task extends Item {
    /**
     * Class Constructor.
     * @param name input parameter
     * @param desc input parameter (text)
     */
    public Task(String name, String desc) {
        super(name, desc);
        setName(name);
        setDescription(desc);
    }
}