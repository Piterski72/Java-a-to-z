package ru.nivanov.dao.factory;

import ru.nivanov.dao.AddressDao;
import ru.nivanov.dao.MusicTypeDao;
import ru.nivanov.dao.RoleDao;
import ru.nivanov.dao.UserDao;

/**
 * Created by Nikolay Ivanov on 18.04.2018.
 */
public abstract class DaoFactory {

    public abstract AddressDao getAddressDao();

    public abstract MusicTypeDao getMusicTypeDao();

    public abstract RoleDao getRoleDao();

    public abstract UserDao getUserDao();


}
