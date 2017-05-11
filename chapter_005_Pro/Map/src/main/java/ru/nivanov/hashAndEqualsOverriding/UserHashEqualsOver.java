package ru.nivanov.hashAndEqualsOverriding;

import ru.nivanov.User;

import java.util.Calendar;

/**
 * Created by Nikolay Ivanov on 10.05.2017.
 */
public class UserHashEqualsOver extends User {
    /**
     * Constructor.
     * @param name ..
     * @param children ..
     * @param birthday ..
     */
    UserHashEqualsOver(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserHashEqualsOver o1 = (UserHashEqualsOver) o;

        if (getChildren() != o1.getChildren()) return false;
        if (!getName().equals(o1.getName())) return false;
        return getBirthday().equals(o1.getBirthday());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getChildren();
        result = 31 * result + getBirthday().hashCode();
        return result;
    }
}
