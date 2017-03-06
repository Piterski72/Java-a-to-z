package ru.nivanov.Start;

import ru.nivanov.Foods.GeneralFood;
import ru.nivanov.Storages.*;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 28.02.2017.
 */
public class ControlQuality {
    private ArrayList<GeneralFood> foodList = new ArrayList<>();
    private ArrayList<GeneralStorage> storages = new ArrayList<>();

    /**
     * Constructor.
     * @param foodList ..
     */
    public ControlQuality(ArrayList<GeneralFood> foodList) {
        this.foodList = foodList;
    }

    /**
     * Getter for storiges.
     * @return ..
     */
    public ArrayList<GeneralStorage> getStorages() {
        return storages;
    }

    /**
     *
     */
    public void installStorages() {
        GeneralStorage wareHouse = new WareHouseStorage();
        storages.add(wareHouse);
        storages.add(new ShopStorage());
        storages.add(new TrashStorage());
        storages.add(new ExtendedWareHouse(wareHouse));
        storages.add(new ExtendedReproductFactory(new TrashStorage()));
        storages.add(new ExtendedCoolStorage(wareHouse));
    }

    /**
     * Method for food spreading to storages.
     * @return boolean filing result.
     */
    public boolean setFoodDestination() {
        int count = 0;
        boolean fillStorageOK = false;
        for (GeneralFood aFoodList : foodList) {
            for (GeneralStorage storage : storages) {
                if (storage.checkCondition(aFoodList)) {
                    storage.addFoodItem(aFoodList);
                    count++;
                    break;
                }
            }
        }
        if (count == foodList.size()) {
            fillStorageOK = true;
        }
        System.out.println(String.format("Result of storage filling is: %s", fillStorageOK));
        return fillStorageOK;
    }

    /**
     *
     */
    public void getStoragesInfo() {
        for (GeneralStorage value : storages) {
            value.getStorageInfo();
            System.out.println(System.getProperty("line.separator"));
        }
    }
}
