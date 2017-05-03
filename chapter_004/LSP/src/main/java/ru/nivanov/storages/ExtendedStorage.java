package ru.nivanov.storages;

import ru.nivanov.foods.GeneralFood;

/**
 * Created by Nikolay Ivanov on 03.03.2017.
 */
abstract class ExtendedStorage implements GeneralStorage {
    private static final int TOTAL_SIZE = 100;
    private GeneralStorage generalStorage;

    /**
     * Constructor.
     * @param generalStorage ..
     */
    ExtendedStorage(GeneralStorage generalStorage) {
        this.generalStorage = generalStorage;

    }

    ExtendedStorage() {
    }

    /**
     * Getting total storage size.
     * @return ..
     */
    @Override
    public int getTotalStorageSize() {
        return ExtendedStorage.TOTAL_SIZE;
    }

    /**
     * Check if the food is for this storage.
     * @param item ..
     * @return ..
     */
    @Override
    public boolean checkCondition(GeneralFood item, String date) {
        return this.generalStorage.checkCondition(item, date);
    }

}
