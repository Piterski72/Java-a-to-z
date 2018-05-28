package ru.nivanov.dao;

import ru.nivanov.entity.Address;

import java.util.Collection;

/**
 * Created by Nikolay Ivanov on 08.05.2018.
 */
public interface AddressDao {

    int create(Address entity);

    Address update(int id, Address entity);

    boolean delete(int id);

    Collection<Address> getAll();

    Address getById(int id);
}
