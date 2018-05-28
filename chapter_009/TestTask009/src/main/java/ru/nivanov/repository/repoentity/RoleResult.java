package ru.nivanov.repository.repoentity;

import ru.nivanov.entity.Role;
import ru.nivanov.entity.User;

import java.util.Collection;

/**
 * Created by Nikolay Ivanov on 04.05.2018.
 */
public class RoleResult {

    private Role role;
    private Collection<User> users;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
