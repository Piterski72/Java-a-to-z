package ru.nivanov.repository;

import ru.nivanov.entity.Role;
import ru.nivanov.repository.repoentity.RoleResult;

/**
 * Created by Nikolay Ivanov on 08.05.2018.
 */
public interface RoleRepository {

    RoleResult getEntity(Role role);
}
