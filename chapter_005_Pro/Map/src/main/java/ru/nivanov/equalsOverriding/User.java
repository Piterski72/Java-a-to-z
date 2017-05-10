package ru.nivanov.equalsOverriding;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (children != user.children) return false;
        if (!name.equals(user.name)) return false;
        return birthday.equals(user.birthday);
    }

}
