package ru.nivanov.Storages;

import ru.nivanov.Foods.GeneralFood;

import java.util.ArrayList;

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
     * Get all food from storage.
     * @return all food.
     */
    ArrayList<GeneralFood> getAllFoodFromStorage();

    /**
     * Get storage information.
     */
    void getStorageInfo();

    /**
     * Check if the food is for this storage.
     * @param item ..
     * @param date ..
     * @return ..
     */
    boolean checkCondition(GeneralFood item, String date);

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
