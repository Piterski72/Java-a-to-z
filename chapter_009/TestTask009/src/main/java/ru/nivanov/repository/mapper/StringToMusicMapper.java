package ru.nivanov.repository.mapper;

import ru.nivanov.dao.MusicTypeDao;
import ru.nivanov.dao.factory.DaoFactory;
import ru.nivanov.dao.factory.PostgresDaoFactory;
import ru.nivanov.entity.MusicType;

import java.util.Collection;

/**
 * Created by Nikolay Ivanov on 08.05.2018.
 */
public class StringToMusicMapper implements Mapper<String, MusicType> {
    private DaoFactory factory = new PostgresDaoFactory();
    private MusicTypeDao musicDao = factory.getMusicTypeDao();

    @Override
    public MusicType map(String music) {
        MusicType result = null;

        Collection<MusicType> musTypes = this.musicDao.getAll();
        for (MusicType mus : musTypes) {
            if (mus.getType().equalsIgnoreCase(music)) {
                result = mus;
                break;
            }
        }
        if (result == null) {
            result = new MusicType();
            result.setType(music);
            result.setId(this.musicDao.create(result));
        }
        return result;
    }
}
