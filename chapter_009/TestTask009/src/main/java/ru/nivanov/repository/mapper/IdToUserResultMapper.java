package ru.nivanov.repository.mapper;

import ru.nivanov.dao.AddressDao;
import ru.nivanov.dao.MusicTypeDao;
import ru.nivanov.dao.RoleDao;
import ru.nivanov.dao.UserDao;
import ru.nivanov.dao.factory.DaoFactory;
import ru.nivanov.dao.factory.PostgresDaoFactory;
import ru.nivanov.entity.Address;
import ru.nivanov.entity.MusicType;
import ru.nivanov.entity.Role;
import ru.nivanov.entity.User;
import ru.nivanov.repository.repoentity.UserResult;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Nikolay Ivanov on 06.05.2018.
 */
public class IdToUserResultMapper implements Mapper<Integer, UserResult> {
    private final DaoFactory factory = new PostgresDaoFactory();
    private final AddressDao addressDao = factory.getAddressDao();
    private final MusicTypeDao musicDao = factory.getMusicTypeDao();
    private final RoleDao roleDao = factory.getRoleDao();
    private final UserDao userDao = factory.getUserDao();

    @Override
    public UserResult map(Integer val) {
        User user = userDao.getById(val);
        Address addr = addressDao.getById(user.getAddressid());
        Role role = roleDao.getById(user.getRoleid());
        Set<Integer> musicSet = user.getMusicType();
        Set<MusicType> musicTypes = new CopyOnWriteArraySet<>();
        for (Integer musnum : musicSet) {
            musicTypes.add(musicDao.getById(musnum));
        }
        UserResult userResult = new UserResult();
        userResult.setUser(user);
        userResult.setAddress(addr);
        userResult.setRole(role);
        userResult.setTypes(musicTypes);

        return userResult;
    }
}
