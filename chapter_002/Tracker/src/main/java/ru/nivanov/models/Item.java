package ru.nivanov.models;

import java.util.ArrayList;

/**
 * Item class.
 * @author nivanov.
 */
public class Item {
    /**
     */
    private final ArrayList<String> comments = new ArrayList<>();
    /**
     */
    private String id;
    /**
     */
    private String name;
    /**
     */
    private String description;
    /**
     */
    private long create;

    /**
     */
    Item() {
    }

    /**
     * Class Constructor.
     * @param name input parameter
     * @param description input parameter
     * @param create input parameter
     */
    public Item(String name, String description, long create) {
        this.name = name;
        this.description = description;
        this.create = create;
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
    public void setName(String name) {
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
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getting create info.
     * @return create
     */
    public long getCreate() {
        return this.create;
    }

    /**
     * Setting create.
     * @param create input param
     */
    void setCreate(long create) {
        this.create = create;
    }

    /**
     * Getting id.
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Setting id.
     * @param id input param
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Add new comments.
     * @param comments input param
     */
    public void addCommentz(String comments) {
        this.comments.add(comments);
    }

    /**
     * Getting comment text.
     * @return commResult massive
     */
    public ArrayList<String> getComments() {
        return this.comments;
    }
}