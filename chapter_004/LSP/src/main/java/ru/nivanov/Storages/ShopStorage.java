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
    private static int storageSize = 0;
    private ArrayList<GeneralFood> shopFoods = new ArrayList<>();

    /**
     * Getter.
     * @return ..
     */
    public ArrayList<GeneralFood> getShopFoods() {
        return shopFoods;
    }

    /**
     * Add item.
     * @param item ..
     */
    @Override
    public void addFoodItem(GeneralFood item) {
        double itemLife = item.getShelfLifePercent();
        if (itemLife > TWENTYFIVE & itemLife <= SEVENTYFIVE) {
            this.shopFoods.add(item);
        } else if (itemLife > SEVENTYFIVE & itemLife <= STO) {
            item.setNewPrice(item.getPrice() * (1.00 - (double) item.getDiscount() / STO));
            this.shopFoods.add(item);
        }
    }

    /**
     * Remove item.
     * @param item ..
     */
    @Override
    public void removeFoodItem(GeneralFood item) {
        this.shopFoods.remove(item);
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
    public boolean checkCondition(GeneralFood value) {
        double foodLife = value.getShelfLifePercent();
        return (foodLife > TWENTYFIVE & foodLife < STO);
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
