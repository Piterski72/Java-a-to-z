package ru.nivanov.hashOverriding;

import ru.nivanov.User;

import java.util.Calendar;

/**
 * Created by Nikolay Ivanov on 10.05.2017.
 */
public class UserHashOver extends User {
    /**
     * Constructor.
     * @param name ..
     * @param children ..
     * @param birthday ..
     */
    public UserHashOver(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getChildren();
        result = 31 * result + getBirthday().hashCode();
        return result;
    }
}
