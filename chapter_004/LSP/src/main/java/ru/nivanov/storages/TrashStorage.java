package ru.nivanov.storages;

import ru.nivanov.foods.GeneralFood;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 28.02.2017.
 */
public class TrashStorage implements GeneralStorage {
    private static final int TOTAL_SIZE = 100;
    private static final int STO = 100;
    private ArrayList<GeneralFood> trashFoods = new ArrayList<>();

    /**
     * Add item.
     * @param item ..
     */
    @Override
    public void addFoodItem(GeneralFood item) {
        this.trashFoods.add(item);
    }

    /**
     * Remove item.
     */
    @Override
    public ArrayList<GeneralFood> getAllFoodFromStorage() {
        return this.trashFoods;


    }

    /**
     *
     */
    @Override
    public void getStorageInfo() {
        System.out.println("TrashStorage information");
        for (GeneralFood items : getAllFoodFromStorage()) {
            System.out.println(items);
        }
    }

    /**
     * @return ..
     */
    @Override
    public boolean checkCondition(GeneralFood value, String data) {
        return (value.getShelfLifePercent(data) > STO);
    }

    /**
     * Getting storage size.
     * @return ..
     */
    @Override
    public int getTotalStorageSize() {
        return TrashStorage.TOTAL_SIZE;
    }

    /**
     * Getting current storage size.
     * @return ..
     */
    @Override
    public int getUsedStorageSize() {
        return this.trashFoods.size();
    }
}
