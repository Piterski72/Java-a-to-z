package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 17.04.2017.
 */
class User implements Comparable<User> {
    private final String name;
    private final int age;

    /**
     * Constructor.
     * @param name ..
     * @param age ..
     */
    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Name getter.
     * @return ..
     */
    public String getName() {
        return name;
    }

    /**
     * Age getter.
     * @return ..
     */
    public int getAge() {
        return age;
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
        return Integer.valueOf(age).compareTo(user.age);
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}
