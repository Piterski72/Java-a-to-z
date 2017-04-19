package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 18.04.2017.
 */
class User implements Comparable<User> {
    private String name;
    private int passport;

    /**
     * Constructor.
     * @param name ..
     * @param passport ..
     */
    User(String name, int passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Name getter.
     * @return ..
     */
    String getName() {
        return name;
    }

    /**
     * pasport getter.
     * @return ..
     */
    int getPassport() {
        return passport;
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

        return (getPassport() == user.getPassport() && getName().equals(user.getName()));
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        final int hashNumber = 31;
        result = hashNumber * result + getPassport();
        return result;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * @param user the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException if the specified object's type prevents it
     * from being compared to this object.
     */
    @Override
    public int compareTo(User user) {
        int compareResult = 0;
        if (this.equals(user)) {
            compareResult = 0;
        } else if (this.hashCode() != user.hashCode()) {
            compareResult = (this.hashCode() - user.hashCode());
        }
        return compareResult;
    }
}
