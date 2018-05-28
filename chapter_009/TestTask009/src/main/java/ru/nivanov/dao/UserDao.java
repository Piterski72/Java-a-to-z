package ru.nivanov.dao;

import ru.nivanov.entity.User;

import java.util.Collection;

/**
 * Created by Nikolay Ivanov on 08.05.2018.
 */
public interface UserDao {

    int create(User entity);

    User update(int id, User entity);

    boolean delete(int id);

    Collection<User> getAll();

    User getById(int id);
}
