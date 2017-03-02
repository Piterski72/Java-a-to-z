package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 28.02.2017.
 */
interface GeneralStorage {
    /**
     * Add Item.
     * @param item ..
     */
    void addFoodItem(Food item);

    /**
     * Remove item.
     * @param item ..
     */
    void removeFoodItem(Food item);

    /**
     * Get storage information.
     */
    void getStorageInfo();

    /**
     * Check if the food is for this storage.
     * @param value ..
     * @return ..
     */
    boolean checkCondition(double value);
}
