package ru.nivanov.store;

/**
 * Created by Nikolay Ivanov on 28.04.2017.
 */
class User extends Base {
    private String userId;

    /**
     * Constructor.
     * @param userId ..
     */
    User(String userId) {
        this.userId = userId;
    }

    @Override
    String getId() {
        return this.userId;
    }

    @Override
    void setId(String id) {
        this.userId = id;


    }
}
