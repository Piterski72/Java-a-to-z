package ru.nivanov.tracker.models;

/**
 * Item class.
 * @author nivanov.
 */
public class Item {

    private String name;
    private String description;

    /**
     * Class Constructor.
     * @param name input parameter
     * @param description input parameter
     */
    Item(String name, String description) {
        this.name = name;
        this.description = description;

    }

    /**
     * Getting name.
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setting name.
     * @param name input param
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * Getting description.
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setting description.
     * @param description input param
     */
    void setDescription(String description) {
        this.description = description;
    }

}