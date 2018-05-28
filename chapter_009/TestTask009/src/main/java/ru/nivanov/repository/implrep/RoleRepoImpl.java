package ru.nivanov.repository.implrep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nivanov.dao.factory.DaoFactory;
import ru.nivanov.dao.factory.PostgresDaoFactory;
import ru.nivanov.entity.Role;
import ru.nivanov.repository.RoleRepository;
import ru.nivanov.repository.mapper.IdToRoleResultMapper;
import ru.nivanov.repository.mapper.Mapper;
import ru.nivanov.repository.repoentity.RoleResult;

/**
 * Created by Nikolay Ivanov on 04.05.2018.
 */
public class RoleRepoImpl implements RoleRepository {

    private static final Logger LOG = LoggerFactory.getLogger(RoleRepoImpl.class);
    private DaoFactory factory = new PostgresDaoFactory();
    private Mapper<Integer, RoleResult> intToRoleresultMap = new IdToRoleResultMapper();

    @Override
    public RoleResult getEntity(Role role) {
        return this.intToRoleresultMap.map(role.getId());
    }
}
