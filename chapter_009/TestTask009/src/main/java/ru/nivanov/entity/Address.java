package ru.nivanov.entity;

import java.io.Serializable;

/**
 * Created by Nikolay Ivanov on 10.04.2018.
 */
public class Address implements Serializable {
    private String location;
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return getId() == address.getId() && getLocation().equals(address.getLocation());
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int result = getLocation().hashCode();
        result = 31 * result + getId();
        return result;
    }
}
