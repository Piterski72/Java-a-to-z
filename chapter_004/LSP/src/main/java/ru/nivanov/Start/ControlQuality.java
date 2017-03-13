package ru.nivanov.Start;

import ru.nivanov.Foods.GeneralFood;
import ru.nivanov.Storages.*;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 28.02.2017.
 */
public class ControlQuality {
    private int count = 0;
    private ArrayList<GeneralFood> foodList = new ArrayList<>();
    private boolean[] checkInStorage;
    private ArrayList<GeneralStorage> mainStorages = new ArrayList<>();
    private ArrayList<GeneralStorage> specialStorages = new ArrayList<>();

    /**
     * Constructor.
     * @param foodList ..
     */
    public ControlQuality(ArrayList<GeneralFood> foodList) {
        this.foodList = foodList;
        this.checkInStorage = new boolean[foodList.size()];
    }

    /**
     * Getter for main storiges.
     * @return ..
     */
    public ArrayList<GeneralStorage> getMainStorages() {
        return mainStorages;
    }

    /**
     * Getter for special storiges.
     * @return ..
     */
    public ArrayList<GeneralStorage> getSpecialStorages() {
        return specialStorages;
    }

    /**
     * setting up main storages.
     */
    public void installMainStorages() {
        WareHouseStorage wareHouseStorage = new WareHouseStorage();
        mainStorages.add(wareHouseStorage);
        mainStorages.add(new ShopStorage());
        mainStorages.add(new TrashStorage());
        mainStorages.add(new ExtendedWareHouse(wareHouseStorage));

    }

    /**
     * setting up special storages.
     */
    public void installSpecialStorages() {
        specialStorages.add(new ExtendedReproductFactory(new TrashStorage()));
        specialStorages.add(new ExtendedCoolStorage(new WareHouseStorage()));
    }

    /**
     * Method for food spreading to main storages.
     */
    private void setFoodDestination() {
        for (int i = 0; i < foodList.size(); i++) {
            for (GeneralStorage storage : mainStorages) {
                if (storage.checkCondition(foodList.get(i)) && (!this.checkInStorage[i])) {
                    storage.addFoodItem(foodList.get(i));
                    count++;
                    break;
                }
            }
        }
    }

    /**
     * Method for food spreading to special storages.
     */
    private void setSpecialFoodDestination() {
        for (int i = 0; i < foodList.size(); i++) {
            for (GeneralStorage storage : specialStorages) {
                if (storage.checkCondition(foodList.get(i))) {
                    storage.addFoodItem(foodList.get(i));
                    this.checkInStorage[i] = true;
                    count++;
                    break;
                }
            }
        }
    }

    /**
     * All food to all storages. First, use special storages then main storages.
     */
    public void setAllFoodDestination() {
        boolean fillStorageOK = false;
        setSpecialFoodDestination();
        setFoodDestination();
        if (count == foodList.size()) {
            fillStorageOK = true;
        }
        System.out.println(String.format("Result of storage filling is: %s", fillStorageOK));
    }

    /**
     * main storages info.
     */
    public void getMainStoragesInfo() {
        for (GeneralStorage value : mainStorages) {
            value.getStorageInfo();
            System.out.println(System.getProperty("line.separator"));
        }
    }

    /**
     * special storages info.
     */
    public void getSpecialStoragesInfo() {
        for (GeneralStorage value : specialStorages) {
            value.getStorageInfo();
            System.out.println(System.getProperty("line.separator"));
        }
    }
}
