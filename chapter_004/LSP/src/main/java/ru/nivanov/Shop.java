package ru.nivanov;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 28.02.2017.
 */
public class Shop implements GeneralStorage {
    private static final int TWENTYFIVE = 25;
    private static final int SEVENTYFIVE = 75;
    private static final int STO = 100;
    private ArrayList<Food> shopFoods = new ArrayList<>();

    /**
     * Getter.
     * @return ..
     */
    public ArrayList<Food> getShopFoods() {
        return shopFoods;
    }

    /**
     * Add item.
     * @param item ..
     */
    @Override
    public void addFoodItem(Food item) {
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
    public void removeFoodItem(Food item) {
        this.shopFoods.remove(item);
    }

    /**
     *
     */
    @Override
    public void getStorageInfo() {
        System.out.println("Shop information");
        for (Food items : this.shopFoods) {
            System.out.println(items);
        }
    }

    /**
     * @return ..
     */
    @Override
    public boolean checkCondition(double foodLife) {
        return (foodLife > TWENTYFIVE & foodLife < STO);
    }
}
