package ru.nivanov.repository.implrep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nivanov.baseutils.NoConnectException;
import ru.nivanov.baseutils.PostgresBaseUtils;
import ru.nivanov.dao.AddressDao;
import ru.nivanov.dao.UserDao;
import ru.nivanov.dao.factory.DaoFactory;
import ru.nivanov.dao.factory.PostgresDaoFactory;
import ru.nivanov.entity.Address;
import ru.nivanov.entity.MusicType;
import ru.nivanov.entity.Role;
import ru.nivanov.entity.User;
import ru.nivanov.repository.UserRepository;
import ru.nivanov.repository.mapper.IdToUserResultMapper;
import ru.nivanov.repository.mapper.Mapper;
import ru.nivanov.repository.mapper.StringToMusicMapper;
import ru.nivanov.repository.mapper.StringToRoleMapper;
import ru.nivanov.repository.repoentity.UserResult;
import ru.nivanov.repository.specification.Specification;
import ru.nivanov.repository.specification.SqlSpec;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Nikolay Ivanov on 04.05.2018.
 */
public class UserRepoImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(UserRepoImpl.class);
    private final DaoFactory factory = new PostgresDaoFactory();
    private final AddressDao addressDao = factory.getAddressDao();
    private final UserDao userDao = factory.getUserDao();
    private final Mapper<Integer, UserResult> intToUsrMap = new IdToUserResultMapper();
    private final Mapper<String, MusicType> strToMusMap = new StringToMusicMapper();
    private final Mapper<String, Role> strToRole = new StringToRoleMapper();

    @Override
    public UserResult getEntity(User entity) {
        return intToUsrMap.map(entity.getId());
    }

    @Override
    public boolean saveEntity(String name, String location, String rolename, String[] music) {
        User user = new User();
        user.setName(name);

        Address addr = new Address();
        addr.setLocation(location);
        int addrid = addressDao.create(addr);
        user.setAddressid(addrid);

        Role role = this.strToRole.map(rolename);
        if (!role.getRolename().equals("empty")) {
            user.setRoleid(role.getId());
        }
        Set<Integer> usermusicId = new CopyOnWriteArraySet<>();
        for (String mus : music) {
            MusicType current = strToMusMap.map(mus);
            usermusicId.add(current.getId());
        }
        user.setMusicTypeList(usermusicId);
        int result = this.userDao.create(user);

        return result != -1;
    }

    @Override
    public void updateEntity(int id, String name, String location, String rolename, String[] music) {
        User updated = this.userDao.getById(id);
        updated.setName(name);

        Address updAddr = this.addressDao.getById(updated.getAddressid());
        updAddr.setLocation(location);
        this.addressDao.update(updated.getAddressid(), updAddr);

        Role updRole = this.strToRole.map(rolename);
        if (!updRole.getRolename().equals("empty")) {
            updated.setRoleid(updRole.getId());
        }
        Set<Integer> intMus = new CopyOnWriteArraySet<>();
        for (String mus : music) {
            MusicType current = this.strToMusMap.map(mus);
            intMus.add(current.getId());
        }
        updated.setMusicTypeList(intMus);
        this.userDao.update(id, updated);
    }

    @Override
    public List<UserResult> query(Specification spec) {

        SqlSpec sqlSpec = (SqlSpec) spec;
        List<UserResult> usr = new CopyOnWriteArrayList<>();
        Set<Integer> userIds = new CopyOnWriteArraySet<>();

        String query = sqlSpec.toSqlQuery();

        try (Connection conn = PostgresBaseUtils.getBase().getConnect();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                int userid = rs.getInt(1);
                userIds.add(userid);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (NoConnectException e) {
            LOG.error(e.getMessage(), e);
        }
        for (Integer usId : userIds) {
            usr.add(this.intToUsrMap.map(usId));
        }
        return usr;
    }

}
