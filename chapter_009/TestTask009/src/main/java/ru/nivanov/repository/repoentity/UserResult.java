package ru.nivanov.repository.repoentity;

import ru.nivanov.entity.Address;
import ru.nivanov.entity.MusicType;
import ru.nivanov.entity.Role;
import ru.nivanov.entity.User;

import java.util.Set;

/**
 * Created by Nikolay Ivanov on 04.05.2018.
 */
public class UserResult {
    private User user;
    private Address address;
    private Role role;
    private Set<MusicType> types;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<MusicType> getTypes() {
        return types;
    }

    public void setTypes(Set<MusicType> types) {
        this.types = types;
    }
}
