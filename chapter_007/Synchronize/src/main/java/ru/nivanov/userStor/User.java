package ru.nivanov.userStor;

/**
 * Created by Nikolay Ivanov on 30.06.2017.
 */
class User {

    private final int id;
    private String name;
    private int account;

    /**
     * Constructor.
     * @param name ..
     * @param id ..
     */
    User(String name, int id) {
        this.name = name;
        this.account = 0;
        this.id = id;
    }

    /**
     * Name getter.
     * @return ..
     */
    private String getName() {
        return name;
    }

    /**
     * Name setter.
     * @param name ..
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Account getter.
     * @return ..
     */
    int getAccount() {
        return account;
    }

    /**
     * Account setter.
     * @param account ..
     */
    void setAccount(int account) {
        this.account = account;
    }

    /**
     * Id getter.
     * @return ..
     */
    int getId() {
        return id;
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

        return getAccount() == user.getAccount() && getId() == user.getId() && getName().equals(user.getName());
    }

    @Override
    public int hashCode() {
        final int value = 31;
        int result = getName().hashCode();
        result = value * result + getAccount();
        result = value * result + getId();
        return result;
    }
}
