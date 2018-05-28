package ru.nivanov.entity;

import java.io.Serializable;

/**
 * Created by Nikolay Ivanov on 10.04.2018.
 */
public class MusicType implements Serializable {
    private String type;
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MusicType musicType = (MusicType) o;

        return getId() == musicType.getId() && getType().equals(musicType.getType());
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int result = getType().hashCode();
        result = 31 * result + getId();
        return result;
    }
}
