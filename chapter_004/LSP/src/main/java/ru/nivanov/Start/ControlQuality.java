package ru.nivanov.Start;

import ru.nivanov.Foods.GeneralFood;
import ru.nivanov.Storages.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Nikolay Ivanov on 28.02.2017.
 */
public class ControlQuality {
    private String checkDate = "01.03.2017";
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
     * Date of starting storage getter.
     * @return ..
     */
    private String getCheckDate() {
        return this.checkDate;
    }

    /**
     * New start storage date setter.
     * @param checkDate ..
     */
    private void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
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
    private ArrayList<GeneralStorage> getSpecialStorages() {
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
     * @param date ..
     */
    private void setFoodDestination(String date) {
        for (int i = 0; i < this.foodList.size(); i++) {
            for (GeneralStorage storage : mainStorages) {
                if (storage.checkCondition(this.foodList.get(i), date) && (!this.checkInStorage[i])) {
                    storage.addFoodItem(this.foodList.get(i));
                    count++;
                    break;
                }
            }
        }
    }

    /**
     * Method for food spreading to special storages.
     * @param date ..
     */
    private void setSpecialFoodDestination(String date) {
        for (int i = 0; i < this.foodList.size(); i++) {
            for (GeneralStorage storage : specialStorages) {
                if (storage.checkCondition(this.foodList.get(i), date)) {
                    storage.addFoodItem(this.foodList.get(i));
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
        System.out.println(String.format("Information valid upon: %s", getCheckDate()));
        System.out.println(System.getProperty("line.separator"));
        boolean fillStorageOK = false;
        setSpecialFoodDestination(getCheckDate());
        setFoodDestination(getCheckDate());
        if (count == this.foodList.size()) {
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

    /**
     * Resort food using current date.
     */
    public void resort() {
        this.foodList.clear();
        ArrayList<GeneralStorage> tempStorages = new ArrayList<>();
        tempStorages.addAll(getMainStorages());
        tempStorages.addAll(getSpecialStorages());
        for (GeneralStorage storage : tempStorages) {
            this.foodList.addAll(storage.getAllFoodFromStorage());
        }
        for (GeneralStorage mainStorages : getMainStorages()) {
            mainStorages.getAllFoodFromStorage().clear();
        }
        for (GeneralStorage specialStorages : getSpecialStorages()) {
            specialStorages.getAllFoodFromStorage().clear();
        }
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd.MM.yyyy");
        String currentDate = dayFormat.format(new Date());
        setCheckDate(currentDate);
        this.count = 0;
        this.checkInStorage = new boolean[this.foodList.size()];
        setAllFoodDestination();
        getMainStoragesInfo();
        getSpecialStoragesInfo();

    }
}
