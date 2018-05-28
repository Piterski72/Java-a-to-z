package ru.nivanov.entity;

import java.io.Serializable;

/**
 * Created by Nikolay Ivanov on 10.04.2018.
 */
public class Role implements Serializable {
    private String rolename;
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return getId() == role.getId() && getRolename().equals(role.getRolename());
    }

    public int getId() {
        return id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int result = getRolename().hashCode();
        result = 31 * result + getId();
        return result;
    }
}
