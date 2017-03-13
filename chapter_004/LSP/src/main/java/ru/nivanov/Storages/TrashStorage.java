package ru.nivanov.Storages;

import ru.nivanov.Foods.GeneralFood;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 28.02.2017.
 */
public class TrashStorage implements GeneralStorage {
    private static final int TOTAL_SIZE = 100;
    private static final int STO = 100;
    private static int storageSize;
    private ArrayList<GeneralFood> trashFoods = new ArrayList<>();

    /**
     * Getter for trashFoods.
     * @return ..
     */
    public ArrayList<GeneralFood> getTrashFoods() {
        return trashFoods;
    }

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
     * @param item ..
     */
    @Override
    public void removeFoodItem(GeneralFood item) {
        this.trashFoods.remove(item);


    }

    /**
     *
     */
    @Override
    public void getStorageInfo() {
        System.out.println("TrashStorage information");
        for (GeneralFood items : getTrashFoods()) {
            System.out.println(items);
        }
    }

    /**
     * @return ..
     */
    @Override
    public boolean checkCondition(GeneralFood value) {
        return (value.getShelfLifePercent() > STO);
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
