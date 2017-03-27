package ru.nivanov.Start;

import ru.nivanov.Foods.*;

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
        ArrayList<GeneralFood> foodProducts = new ArrayList<>();
        foodProducts.add(new MilkFood("kefir", NINETEEN, "11.03.2017", "11.06.2017"));
        foodProducts.add(new MilkFood("smetana", SEVEN, "12.03.2017", "11.04.2017"));
        foodProducts.add(new MilkFood("cheese", 2, "12.03.2017", "18.05.2017"));
        foodProducts.add(new MilkFood("cream", TEN, "12.03.2017", "01.04.2017"));
        foodProducts.add(new VegetableFood("potato", SIX, "11.12.2016", "11.02.2017"));
        foodProducts.add(new VegetableFood("cabbage", TEN, "11.01.2017", "11.03.2017"));
        foodProducts.add(new VegetableFood("parrot", TEN, "01.03.2017", "11.04.2017"));
        foodProducts.add(new VegetableFood("svekla", TEN, "01.03.2017", "11.04.2017"));
        foodProducts.add(new ReproductFood("tvorog", TEN, "01.02.2017", "28.02.2017"));
        foodProducts.add(new LowTempVegetables("tomato", SEVEN, "11.03.2017", "30.03.2017"));
        ControlQuality foodControl = new ControlQuality(foodProducts);
        foodControl.installMainStorages();
        foodControl.installSpecialStorages();
        foodControl.setAllFoodDestination();
        foodControl.getMainStoragesInfo();
        foodControl.getSpecialStoragesInfo();

        foodControl.resort();

    }
}
