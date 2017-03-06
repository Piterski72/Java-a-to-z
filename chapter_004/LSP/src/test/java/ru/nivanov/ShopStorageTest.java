package ru.nivanov;

import org.junit.Test;
import ru.nivanov.Foods.GeneralFood;
import ru.nivanov.Foods.MilkFood;
import ru.nivanov.Foods.VegetableFood;
import ru.nivanov.Storages.ShopStorage;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 02.03.2017.
 */
public class ShopStorageTest {
    private static final int TEN = 10;
    private ShopStorage underTest = new ShopStorage();

    /**
     * Test for adding items.
     */
    @Test
    public void whenAddFoodItemThenReturnResult() {
        GeneralFood foodOne = new MilkFood("milk", TEN, "22.02.2017", "20.03.2017");
        GeneralFood foodTwo = new VegetableFood("parrot", TEN, "02.01.2017", "12.03.2017");
        GeneralFood[] foods = {foodOne, foodTwo};
        underTest.addFoodItem(foodOne);
        underTest.addFoodItem(foodTwo);
        GeneralFood[] expected = {underTest.getShopFoods().get(0), underTest.getShopFoods().get(1)};
        assertThat(foods, is(expected));

    }

    /**
     * Test for food freshness.
     */
    @Test
    public void whenCheckConditionThenReturnResult() {
        GeneralFood foodOne = new MilkFood("milk", TEN, "02.03.2017", "20.03.2017");
        GeneralFood foodTwo = new VegetableFood("parrot", TEN, "02.01.2017", "10.03.2017");
        GeneralFood foodThree = new VegetableFood("onion", TEN, "02.01.2017", "12.02.2017");
        boolean resultOne = underTest.checkCondition(foodOne);
        boolean resultTwo = underTest.checkCondition(foodTwo);
        boolean resultThree = underTest.checkCondition(foodThree);
        boolean[] test = {resultOne, resultTwo, resultThree};
        boolean[] expected = {true, true, false};
        assertThat(test, is(expected));

    }

}