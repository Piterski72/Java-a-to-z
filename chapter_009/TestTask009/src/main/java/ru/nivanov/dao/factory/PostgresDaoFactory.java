package ru.nivanov.dao.factory;

import ru.nivanov.dao.AddressDao;
import ru.nivanov.dao.MusicTypeDao;
import ru.nivanov.dao.RoleDao;
import ru.nivanov.dao.UserDao;
import ru.nivanov.dao.impl.PostgresAddressDao;
import ru.nivanov.dao.impl.PostgresMusicTypeDao;
import ru.nivanov.dao.impl.PostgresRoleDao;
import ru.nivanov.dao.impl.PostgresUserDao;

/**
 * Created by Nikolay Ivanov on 18.04.2018.
 */
public class PostgresDaoFactory extends DaoFactory {

    @Override
    public AddressDao getAddressDao() {
        return new PostgresAddressDao();
    }

    @Override
    public MusicTypeDao getMusicTypeDao() {
        return new PostgresMusicTypeDao();
    }

    @Override
    public RoleDao getRoleDao() {
        return new PostgresRoleDao();
    }

    @Override
    public UserDao getUserDao() {
        return new PostgresUserDao();
    }
}

