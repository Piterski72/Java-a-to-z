package ru.nivanov.Storages;

import ru.nivanov.Foods.GeneralFood;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 03.03.2017.
 */
public class ExtendedReproductFactory extends ExtendedStorage {
    /**
     * This field is for identifying reproduct food.
     */
    private static final String CAN_REPRODUCT = "CAN_REPRODUCT";
    private static final int STO = 100;
    private ArrayList<GeneralFood> reproductFactoryFoods = new ArrayList<>();

    /**
     * Constructor.
     * @param generalStorage ..
     */
    public ExtendedReproductFactory(GeneralStorage generalStorage) {
        super(generalStorage);
    }

    /**
     * Add Item.
     * @param item ..
     */
    public void addFoodItem(GeneralFood item) {
        reproductFactoryFoods.add(item);
    }

    /**
     * Remove item.
     */
    @Override
    public ArrayList<GeneralFood> getAllFoodFromStorage() {
        return this.reproductFactoryFoods;
    }

    /**
     * Get storage information.
     */
    @Override
    public void getStorageInfo() {
        System.out.println("Reproduct factory information");
        for (GeneralFood items : this.reproductFactoryFoods) {
            System.out.println(items);

        }

    }

    /**
     * Check if the food is for this storage.
     * @param item ..
     * @return ..
     */
    @Override
    public boolean checkCondition(GeneralFood item, String date) {
        return (item.getShelfLifePercent(date) > STO & (item.getName().contains(CAN_REPRODUCT)));
    }

    /**
     * Getting current storage size.
     * @return ..
     */
    @Override
    public int getUsedStorageSize() {
        return this.reproductFactoryFoods.size();
    }
}
