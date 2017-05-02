package ru.nivanov.Store;

/**
 * Created by Nikolay Ivanov on 28.04.2017.
 */
class Role extends Base {
    private String roleId;

    /**
     * Constructor.
     * @param roleId ..
     */
    Role(String roleId) {
        this.roleId = roleId;
    }

    @Override
    String getId() {
        return this.roleId;
    }

    @Override
    void setId(String newId) {
        this.roleId = newId;


    }
}
