package ru.nivanov.Storages;

import ru.nivanov.Foods.GeneralFood;

/**
 * Created by Nikolay Ivanov on 28.02.2017.
 */
public interface GeneralStorage {
    /**
     * Add Item.
     * @param item ..
     */
    void addFoodItem(GeneralFood item);

    /**
     * Remove item.
     * @param item ..
     */
    void removeFoodItem(GeneralFood item);

    /**
     * Get storage information.
     */
    void getStorageInfo();

    /**
     * Check if the food is for this storage.
     * @param item ..
     * @return ..
     */
    boolean checkCondition(GeneralFood item);

    /**
     * Getting total storage size.
     * @return ..
     */
    int getTotalStorageSize();

    /**
     * Getting current storage size.
     * @return ..
     */
    int getUsedStorageSize();


}
