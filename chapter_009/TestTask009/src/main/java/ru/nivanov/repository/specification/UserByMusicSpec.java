package ru.nivanov.repository.specification;

import ru.nivanov.entity.MusicType;

/**
 * Created by Nikolay Ivanov on 07.05.2018.
 */
public class UserByMusicSpec implements SqlSpec {
    private final MusicType musicType;

    /**
     * Constructor.
     * @param musicType ..
     */
    public UserByMusicSpec(MusicType musicType) {
        this.musicType = musicType;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT %1$s FROM %2$s WHERE %3$s=%4$d", "iduser", "public.usermusik", "idmusik",
                this.musicType.getId());
    }
}
