package ru.nivanov.model;

/**
 * Created by Nikolay Ivanov on 24.10.2017.
 */
public class User {

    private String name;
    private String login;
    private String email;
    private long createDate;
    private Role role;
    private String password;

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

        if (getCreateDate() != user.getCreateDate()) {
            return false;
        }
        if (!getName().equals(user.getName())) {
            return false;
        }
        if (!getLogin().equals(user.getLogin())) {
            return false;
        }
        return getEmail().equals(user.getEmail()) && getRole().equals(user.getRole());
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

    /**
     * Role getter.
     * @return ..
     */
    public Role getRole() {
        return role;
    }

    /**
     * Role setter.
     * @param role ..
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Pass getter.
     * @return ..
     */
    public String getPassword() {
        return password;
    }

    /**
     * Password setter.
     * @param password ..
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getLogin().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + (int) (getCreateDate() ^ (getCreateDate() >>> 32));
        result = 31 * result + getRole().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", login='" + login + '\'' + ", email='" + email + '\'' + ", createDate=" + createDate + ", role=" + role + '}';
    }
}
