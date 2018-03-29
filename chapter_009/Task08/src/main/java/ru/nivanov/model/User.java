package ru.nivanov.model;

/**
 * Created by Nikolay Ivanov on 24.10.2017.
 */
public class User {

    private final String name;
    private final String login;
    private final String email;
    private final long createDate;
    private final String city;
    private final String country;
    private Role role;
    private String password;

    /**
     * Constructor.
     * @param name ..
     * @param login ..
     * @param email ..
     * @param createDate ..
     */
    public User(String name, String login, String email, String city, String country, long createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.city = city;
        this.country = country;
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getCreateDate() != user.getCreateDate()) return false;
        if (!getName().equals(user.getName())) return false;
        if (!getLogin().equals(user.getLogin())) return false;
        if (!getEmail().equals(user.getEmail())) return false;
        if (!getRole().equals(user.getRole())) return false;
        if (!getPassword().equals(user.getPassword())) return false;
        if (!getCity().equals(user.getCity())) return false;
        return getCountry().equals(user.getCountry());
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

    /**
     * City getter.
     * @return ..
     */
    public String getCity() {
        return city;
    }

    /**
     * Country getter.
     * @return ..
     */
    public String getCountry() {
        return country;
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getLogin().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + (int) (getCreateDate() ^ (getCreateDate() >>> 32));
        result = 31 * result + getRole().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getCity().hashCode();
        result = 31 * result + getCountry().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", login='" + login + '\'' + ", email='" + email + '\'' + ", createDate=" + createDate + ", role=" + role + ", password='" + password + '\'' + ", city='" + city + '\'' + ", country='" + country + '\'' + '}';
    }
}
