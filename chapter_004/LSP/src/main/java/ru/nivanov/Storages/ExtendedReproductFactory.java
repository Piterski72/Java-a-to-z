package ru.nivanov.Storages;

import ru.nivanov.Foods.GeneralFood;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 03.03.2017.
 */
public class ExtendedReproductFactory extends ExtendedStorage {
    private static final int TOTAL_SIZE = 100;
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
    @Override
    public void addFoodItem(GeneralFood item) {
        reproductFactoryFoods.add(item);
    }

    /**
     * Remove item.
     * @param item ..
     */
    @Override
    public void removeFoodItem(GeneralFood item) {
        reproductFactoryFoods.remove(item);
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
    public boolean checkCondition(GeneralFood item) {
        TrashStorage trash = new TrashStorage();
        boolean checkTrashCondition = trash.checkCondition(item);
        return (item.getShelfLifePercent() > STO & (!checkTrashCondition));
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
