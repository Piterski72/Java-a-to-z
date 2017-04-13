package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 13.04.2017.
 */
class User {
    private final int id;
    private final String name;
    private final String city;

    /**
     * Constructor.
     * @param id ..
     * @param name ..
     * @param city ..
     */
    User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Getter id.
     * @return ..
     */
    public int getId() {
        return id;
    }

    /**
     * Getter name.
     * @return ..
     */
    public String getName() {
        return name;
    }

    /**
     * Getter city.
     * @return ..
     */
    public String getCity() {
        return city;
    }
}
