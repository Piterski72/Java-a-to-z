package ru.nivanov.store;

/**
 * Created by Nikolay Ivanov on 29.04.2017.
 * @param <T> ..
 */
interface Store<T extends Base> {

    /**
     * Add object to collection.
     * @param object ..
     */
    void add(T object);

    /**
     * Delete object from collection.
     * @param id ..
     */
    void delete(String id);

    /**
     * Set new value in collection.
     * @param oldObject ..
     * @param newObject ..
     */
    void update(T oldObject, T newObject);

}

