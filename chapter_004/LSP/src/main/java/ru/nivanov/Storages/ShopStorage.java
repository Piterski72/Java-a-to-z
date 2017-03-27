package ru.nivanov.Storages;

import ru.nivanov.Foods.GeneralFood;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 28.02.2017.
 */
public class ShopStorage implements GeneralStorage {
    private static final int TOTAL_SIZE = 100;
    private static final int TWENTYFIVE = 25;
    private static final int SEVENTYFIVE = 75;
    private static final int STO = 100;
    private ArrayList<GeneralFood> shopFoods = new ArrayList<>();

    /**
     * Add item.
     * @param item ..
     */
    @Override
    public void addFoodItem(GeneralFood item) {
        this.shopFoods.add(item);
    }

    /**
     * Remove item.
     */
    @Override
    public ArrayList<GeneralFood> getAllFoodFromStorage() {
        return this.shopFoods;
    }

    /**
     *
     */
    @Override
    public void getStorageInfo() {
        System.out.println("ShopStorage information");
        for (GeneralFood items : this.shopFoods) {
            System.out.println(items);
        }
    }

    /**
     * @return ..
     */
    @Override
    public boolean checkCondition(GeneralFood value, String date) {
        double foodLife = value.getShelfLifePercent(date);
        if (foodLife > SEVENTYFIVE & foodLife <= STO) {
            value.setNewPrice(value.getPrice() * (1.00 - (double) value.getDiscount() / STO));
        }
        return (foodLife > TWENTYFIVE & foodLife <= STO);
    }

    /**
     * Getting storage size.
     * @return ..
     */
    @Override
    public int getTotalStorageSize() {
        return ShopStorage.TOTAL_SIZE;
    }

    /**
     * Getting current storage size.
     * @return ..
     */
    @Override
    public int getUsedStorageSize() {
        return this.shopFoods.size();
    }
}
