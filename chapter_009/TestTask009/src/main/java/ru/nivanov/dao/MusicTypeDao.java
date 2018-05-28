package ru.nivanov.dao;

import ru.nivanov.entity.MusicType;

import java.util.Collection;

/**
 * Created by Nikolay Ivanov on 08.05.2018.
 */
public interface MusicTypeDao {

    int create(MusicType entity);

    MusicType update(int id, MusicType entity);

    boolean delete(int id);

    Collection<MusicType> getAll();

    MusicType getById(int id);
}
