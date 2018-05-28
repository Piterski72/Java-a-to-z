package ru.nivanov.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nikolay Ivanov on 10.04.2018.
 */
public class User implements Serializable {

    private String name;
    private int id;
    private int roleid;
    private int addressid;
    private Set<Integer> musicTypeSet = new HashSet<>();

    /**
     * Name getter
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter
     * @param name ..
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Id getter
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * ID setter
     * @param id ..
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Role id getter
     * @return ..
     */
    public int getRoleid() {
        return roleid;
    }

    /**
     * Role id setter
     * @param roleid ..
     */
    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    /**
     * Address id getter
     * @return ..
     */
    public int getAddressid() {
        return addressid;
    }

    /**
     * Address id setter
     * @param addressid ..
     */
    public void setAddressid(int addressid) {
        this.addressid = addressid;
    }

    public Set<Integer> getMusicType() {
        return musicTypeSet;
    }

    public void setMusicTypeList(Set<Integer> musicTypeSet) {
        this.musicTypeSet = musicTypeSet;
    }
}
