package ru.nivanov.Storages;

import ru.nivanov.Foods.GeneralFood;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 06.03.2017.
 */
public class ExtendedCoolStorage extends ExtendedStorage {
    /**
     * This field is for identifying low temperature storage food.
     */
    private static final String LOW_TEMP = "LOW_TEMP";
    private static final int TWENTYFIVE = 25;
    private ArrayList<GeneralFood> extendedCoolStorage = new ArrayList<>();

    /**
     * Constructor.
     * @param coolStorage ..
     */
    public ExtendedCoolStorage(GeneralStorage coolStorage) {
        super(coolStorage);
    }

    /**
     * Add Item.
     * @param item ..
     */
    @Override
    public void addFoodItem(GeneralFood item) {
        this.extendedCoolStorage.add(item);

    }

    /**
     * Remove item.
     * @param item ..
     */
    @Override
    public void removeFoodItem(GeneralFood item) {
        this.extendedCoolStorage.remove(item);

    }

    /**
     * Get storage information.
     */
    @Override
    public void getStorageInfo() {
        System.out.println("Cool storage information");
        for (GeneralFood items : this.extendedCoolStorage) {
            System.out.println(items);
        }
    }

    /**
     * Check if the food is for this storage.
     * @param item ..
     * @return ..
     */
    @Override
    public boolean checkCondition(GeneralFood item) {
        boolean checkForCoolStorage = item.getName().contains(LOW_TEMP);
        return (item.getShelfLifePercent() < TWENTYFIVE && checkForCoolStorage);
    }

    /**
     * Getting current storage size.
     * @return ..
     */
    @Override
    public int getUsedStorageSize() {
        return this.extendedCoolStorage.size();
    }
}
