package ru.nivanov.model;

/**
 * Created by Nikolay Ivanov on 01.12.2017.
 */
public class Role {

    private final String name;

    /**
     * Constructor.
     * @param name ..
     */
    public Role(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role role = (Role) o;

        return getName().equals(role.getName());
    }

    /**
     * Role name getter.
     * @return ..
     */
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
