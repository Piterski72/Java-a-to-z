package ru.nivanov.model;

import java.util.Date;

/**
 * Created by Nikolay Ivanov on 24.10.2017.
 */
public class User {

    private String name;
    private String login;
    private String email;
    private long createDate;

    /**
     * Constructor.
     * @param name ..
     * @param login ..
     * @param email ..
     * @param createDate ..
     */
    public User(String name, String login, String email, long createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return getCreateDate() == user.getCreateDate() && getName().equals(user.getName()) && getLogin().equals(
                user.getLogin()) && getEmail().equals(user.getEmail());
    }

    /**
     * Date getter.
     * @return ..
     */
    public long getCreateDate() {
        return createDate;
    }

    /**
     * Name getter.
     * @return ..
     */
    public String getName() {
        return name;
    }

    /**
     * Login getter.
     * @return ..
     */
    public String getLogin() {
        return login;
    }

    /**
     * Email getter.
     * @return ..
     */
    public String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getLogin().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + (int) (getCreateDate() ^ (getCreateDate() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + getName() + '\'' + ", login='" + getLogin() + '\'' + ", email='" + getEmail() + '\'' + ", createDate=" + new Date(
                getCreateDate()) + '}';
    }
}
