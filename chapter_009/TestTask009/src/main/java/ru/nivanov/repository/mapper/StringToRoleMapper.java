package ru.nivanov.repository.mapper;

import ru.nivanov.dao.RoleDao;
import ru.nivanov.dao.factory.DaoFactory;
import ru.nivanov.dao.factory.PostgresDaoFactory;
import ru.nivanov.entity.Role;

import java.util.Collection;

/**
 * Created by Nikolay Ivanov on 12.05.2018.
 */
public class StringToRoleMapper implements Mapper<String, Role> {
    private final DaoFactory factory = new PostgresDaoFactory();
    private final RoleDao roleDao = factory.getRoleDao();

    @Override
    public Role map(String rolename) {
        Role result = null;

        Collection<Role> roles = this.roleDao.getAll();
        for (Role role : roles) {
            if (role.getRolename().equalsIgnoreCase(rolename)) {
                result = role;
                break;
            }
        }
        if (result == null) {
            result = new Role();
            result.setRolename("empty");
        }
        return result;

    }
}
