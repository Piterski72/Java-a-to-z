package ru.nivanov;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 28.02.2017.
 */
class Trash implements GeneralStorage {
    private static final int STO = 100;
    private ArrayList<Food> trashFoods = new ArrayList<>();

    /**
     * Getter for trashFoods.
     * @return ..
     */
    ArrayList<Food> getTrashFoods() {
        return trashFoods;
    }

    /**
     * Add item.
     * @param item ..
     */
    @Override
    public void addFoodItem(Food item) {
        this.trashFoods.add(item);
    }

    /**
     * Remove item.
     * @param item ..
     */
    @Override
    public void removeFoodItem(Food item) {
        this.trashFoods.remove(item);


    }

    /**
     *
     */
    @Override
    public void getStorageInfo() {
        System.out.println("Trash information");
        for (Food items : getTrashFoods()) {
            System.out.println(items);
        }
    }

    /**
     * @return ..
     */
    @Override
    public boolean checkCondition(double foodLife) {
        return (foodLife > STO);
    }
}
