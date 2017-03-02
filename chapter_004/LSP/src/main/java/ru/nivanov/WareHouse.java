package ru.nivanov;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 28.02.2017.
 */
public class WareHouse implements GeneralStorage {
    private static final int TWENTYFIVE = 25;
    private ArrayList<Food> wareHouseFoods = new ArrayList<>();

    /**
     * Add item.
     */
    @Override
    public void addFoodItem(Food item) {
        this.wareHouseFoods.add(item);
    }

    /**
     * Remove item.
     */
    @Override
    public void removeFoodItem(Food item) {
        this.wareHouseFoods.remove(item);
    }

    /**
     * Get storage information.
     */
    @Override
    public void getStorageInfo() {
        System.out.println("Warehouse information");
        for (Food items : this.wareHouseFoods) {
            System.out.println(items);

        }

    }

    /**
     * Check if the food is for this storage.
     * @param foodLife ..
     * @return ..
     */
    @Override
    public boolean checkCondition(double foodLife) {
        return (foodLife <= TWENTYFIVE);
    }
}
