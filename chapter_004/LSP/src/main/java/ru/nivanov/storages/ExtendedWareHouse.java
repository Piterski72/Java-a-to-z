package ru.nivanov.storages;

import ru.nivanov.foods.GeneralFood;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 03.03.2017.
 */
public class ExtendedWareHouse extends WareHouseStorage {
    private static final int TWENTYFIVE = 25;
    private WareHouseStorage generalStorage;
    private ArrayList<GeneralFood> extendedWareHouseFoods = new ArrayList<>();

    /**
     * Constructor.
     * @param generalStorage ..
     */
    public ExtendedWareHouse(WareHouseStorage generalStorage) {
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
     */
    @Override
    public ArrayList<GeneralFood> getAllFoodFromStorage() {
        return this.extendedWareHouseFoods;

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
    public boolean checkCondition(GeneralFood value, String date) {
        boolean checkWarehouseFullness = this.generalStorage.checkCondition(value, date);
        double foodLife = value.getShelfLifePercent(date);
        return (foodLife <= TWENTYFIVE && (!checkWarehouseFullness));
    }

    /**
     * Getting storage size.
     * @return ..
     */
    @Override
    public int getUsedStorageSize() {
        return this.extendedWareHouseFoods.size();
    }


}
