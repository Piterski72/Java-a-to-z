package ru.nivanov.hashOverriding;

import java.util.Calendar;

/**
 * Created by Nikolay Ivanov on 10.05.2017.
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    /**
     * Constructor.
     * @param name ..
     * @param children ..
     * @param birthday ..
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + children;
        result = 31 * result + birthday.hashCode();
        return result;
    }
}
