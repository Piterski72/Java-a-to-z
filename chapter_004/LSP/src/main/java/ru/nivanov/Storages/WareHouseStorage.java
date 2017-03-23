package ru.nivanov.Storages;

import ru.nivanov.Foods.GeneralFood;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 28.02.2017.
 */
public class WareHouseStorage implements GeneralStorage {
    private static final int TOTAL_SIZE = 3;
    private static final int TWENTYFIVE = 25;
    private ArrayList<GeneralFood> wareHouseFoods = new ArrayList<>();

    /**
     * Add item.
     */
    @Override
    public void addFoodItem(GeneralFood item) {
        this.wareHouseFoods.add(item);
    }

    /**
     * Remove item.
     */
    @Override
    public ArrayList<GeneralFood> getAllFoodFromStorage() {
        return this.wareHouseFoods;
    }

    /**
     * Get storage information.
     */
    @Override
    public void getStorageInfo() {
        System.out.println("Warehouse information");
        for (GeneralFood items : this.wareHouseFoods) {
            System.out.println(items);

        }

    }

    /**
     * Check if the food is for this storage.
     * @param value ..
     * @return ..
     */
    @Override
    public boolean checkCondition(GeneralFood value, String date) {
        double foodLife = value.getShelfLifePercent(date);
        return (foodLife <= TWENTYFIVE && checkSizeCondition());
    }

    /**
     * Getting total storage size.
     * @return ..
     */
    @Override
    public int getTotalStorageSize() {
        return WareHouseStorage.TOTAL_SIZE;
    }

    /**
     * Getting current storage size.
     * @return ..
     */
    @Override
    public int getUsedStorageSize() {
        return this.wareHouseFoods.size();
    }

    /**
     * @return ..
     */
    private boolean checkSizeCondition() {
        return (getUsedStorageSize() < getTotalStorageSize());
    }
}
