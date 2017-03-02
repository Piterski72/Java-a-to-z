package ru.nivanov;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 28.02.2017.
 */
public class FoodManager {
    private static final double NINETEEN = 19;
    private static final double SIX = 6;
    private static final double SEVEN = 7;
    private static final double TEN = 10;

    /**
     * Main method.
     * @param args ..
     */
    public static void main(String[] args) {
        ArrayList<Food> foodProducts = new ArrayList<>();
        foodProducts.add(new MilkFood("kefir", NINETEEN, "11.02.2017", "11.03.2017"));
        foodProducts.add(new MilkFood("smetana", SEVEN, "02.03.2017", "11.03.2017"));
        foodProducts.add(new VegetableFood("potato", SIX, "11.12.2016", "11.02.2017"));
        foodProducts.add(new VegetableFood("cabbage", TEN, "11.01.2017", "11.03.2017"));
        ControlQuality foodControl = new ControlQuality(foodProducts);
        foodControl.installStorages();
        foodControl.setFoodDestination();
        foodControl.getStoragesInfo();
    }
}
