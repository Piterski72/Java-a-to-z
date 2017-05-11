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
    public UserHashEqualsOver(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserHashEqualsOver userHashEqualsOver = (UserHashEqualsOver) o;

        if (getChildren() != userHashEqualsOver.getChildren()) return false;
        if (!getName().equals(userHashEqualsOver.getName())) return false;
        return getBirthday().equals(userHashEqualsOver.getBirthday());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getChildren();
        result = 31 * result + getBirthday().hashCode();
        return result;
    }
}
