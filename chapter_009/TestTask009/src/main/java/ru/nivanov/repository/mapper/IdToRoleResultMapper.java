package ru.nivanov.repository.mapper;

import ru.nivanov.dao.RoleDao;
import ru.nivanov.dao.UserDao;
import ru.nivanov.dao.factory.DaoFactory;
import ru.nivanov.dao.factory.PostgresDaoFactory;
import ru.nivanov.entity.Role;
import ru.nivanov.entity.User;
import ru.nivanov.repository.repoentity.RoleResult;

import java.util.Collection;

/**
 * Created by Nikolay Ivanov on 08.05.2018.
 */
public class IdToRoleResultMapper implements Mapper<Integer, RoleResult> {
    private final DaoFactory factory = new PostgresDaoFactory();
    private final RoleDao roleDao = factory.getRoleDao();
    private final UserDao userDao = factory.getUserDao();

    @Override
    public RoleResult map(Integer val) {
        Role role = this.roleDao.getById(val);
        Collection<User> users = this.userDao.getAll();

        RoleResult result = new RoleResult();
        result.setRole(role);
        result.setUsers(users);

        return result;
    }
}
