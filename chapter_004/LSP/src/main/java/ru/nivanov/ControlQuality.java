package ru.nivanov;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 28.02.2017.
 */
class ControlQuality {
    private ArrayList<Food> foodList = new ArrayList<>();
    private ArrayList<GeneralStorage> storages = new ArrayList<>();

    /**
     * Constructor.
     * @param foodList ..
     */
    ControlQuality(ArrayList<Food> foodList) {
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
        storages.add(new WareHouse());
        storages.add(new Shop());
        storages.add(new Trash());

    }

    /**
     * Method for food spreading to storages.
     * @return boolean filing result.
     */
    public boolean setFoodDestination() {
        double foodLife;
        int count = 0;
        boolean fillStorageOK = false;
        for (int i = 0; i < foodList.size(); i++) {
            foodLife = foodList.get(i).getShelfLifePercent();
            for (int j = 0; j < storages.size(); j++) {
                if (storages.get(j).checkCondition(foodLife)) {
                    storages.get(j).addFoodItem(foodList.get(i));
                    count++;
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
