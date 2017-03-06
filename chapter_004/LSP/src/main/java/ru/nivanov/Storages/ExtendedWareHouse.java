package ru.nivanov.Storages;

import ru.nivanov.Foods.GeneralFood;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 03.03.2017.
 */
public class ExtendedWareHouse extends ExtendedStorage {
    private static final int TWENTYFIVE = 25;
    private GeneralStorage generalStorage;
    private ArrayList<GeneralFood> extendedWareHouseFoods = new ArrayList<>();

    /**
     * Constructor.
     * @param generalStorage ..
     */
    public ExtendedWareHouse(GeneralStorage generalStorage) {
        this.generalStorage = generalStorage;
    }

    /**
     * Add Item.
     * @param item ..
     */
    @Override
    public void addFoodItem(GeneralFood item) {
        this.extendedWareHouseFoods.add(item);

    }

    /**
     * Remove item.
     * @param item ..
     */
    @Override
    public void removeFoodItem(GeneralFood item) {
        this.extendedWareHouseFoods.remove(item);

    }

    /**
     * Get storage information.
     */
    @Override
    public void getStorageInfo() {
        System.out.println("Extended Warehouse information");
        for (GeneralFood items : this.extendedWareHouseFoods) {
            System.out.println(items);

        }

    }

    /**
     * Check if the food is for this storage.
     * @param value ..
     * @return ..
     */
    @Override
    public boolean checkCondition(GeneralFood value) {
        boolean checkWarehouseFullness = this.generalStorage.checkCondition(value);
        double foodLife = value.getShelfLifePercent();
        return (foodLife <= TWENTYFIVE & (!checkWarehouseFullness) & (!checkCoolingCondition(value)));
    }

    /**
     * Getting storage size.
     * @return ..
     */
    @Override
    public int getUsedStorageSize() {
        return this.extendedWareHouseFoods.size();
    }

    /**
     * @param value ..
     * @return ..
     */
    private boolean checkCoolingCondition(GeneralFood value) {
        return (value.getName().contains("lowTemp"));
    }

}
