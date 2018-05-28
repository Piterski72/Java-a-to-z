package ru.nivanov.repository;

import ru.nivanov.entity.User;
import ru.nivanov.repository.repoentity.UserResult;
import ru.nivanov.repository.specification.Specification;

import java.util.List;

/**
 * Created by Nikolay Ivanov on 07.05.2018.
 */
public interface UserRepository {
    UserResult getEntity(User user);

    boolean saveEntity(String name, String location, String rolename, String[] music);

    void updateEntity(int id, String name, String location, String rolename, String[] music);

    List<UserResult> query(Specification spec);
}
