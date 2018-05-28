package ru.nivanov.dao;

import ru.nivanov.entity.Role;

import java.util.Collection;

/**
 * Created by Nikolay Ivanov on 08.05.2018.
 */
public interface RoleDao {

    int create(Role entity);

    Role update(int id, Role entity);

    boolean delete(int id);

    Collection<Role> getAll();

    Role getById(int id);
}
