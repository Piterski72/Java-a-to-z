package ru.nivanov.store;

import ru.nivanov.SimpleArray;

import java.util.NoSuchElementException;

/**
 * Created by Nikolay Ivanov on 29.04.2017.
 * @param <T> ..
 */
public class AbstractStore<T extends Base> implements Store<T> {
    private final SimpleArray<T> simpleArray;

    /**
     * Constructor.
     * @param simpleArray ..
     */
    AbstractStore(SimpleArray<T> simpleArray) {
        this.simpleArray = simpleArray;
    }

    /**
     * Add new object.
     * @param object ..
     */
    @Override
    public void add(T object) {
        this.simpleArray.add(object);
    }

    /**
     * Delete by id.
     * @param id ..
     */
    @Override
    public void delete(String id) {
        int foundIndex = findObjectIndexById(id);
        if (foundIndex != -1) {
            this.simpleArray.delete(foundIndex);
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Update method.
     * @param oldObject ..
     * @param newObject ..
     */
    @Override
    public void update(T oldObject, T newObject) {
        int foundIndex = findObjectIndexById(oldObject.getId());
        if (foundIndex != -1) {
            this.simpleArray.update(newObject, foundIndex);
        } else {
            throw new NoSuchElementException();
        }

    }

    /**
     * Finds object index by object id.
     * @param id ..
     * @return found index.
     */
    public int findObjectIndexById(String id) {
        for (int i = 0; i < this.simpleArray.getLength(); i++) {
            if (this.simpleArray.get(i) != null && this.simpleArray.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
