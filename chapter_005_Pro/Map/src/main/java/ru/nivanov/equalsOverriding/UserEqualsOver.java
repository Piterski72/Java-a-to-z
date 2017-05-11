package ru.nivanov.equalsOverriding;

import ru.nivanov.User;

import java.util.Calendar;

/**
 * Created by Nikolay Ivanov on 10.05.2017.
 */
public class UserEqualsOver extends User {
    /**
     * Constructor.
     * @param name ..
     * @param children ..
     * @param birthday ..
     */
    UserEqualsOver(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEqualsOver o1 = (UserEqualsOver) o;

        if (getChildren() != o1.getChildren()) return false;
        if (!getName().equals(o1.getName())) return false;
        return getBirthday().equals(o1.getBirthday());
    }

}
