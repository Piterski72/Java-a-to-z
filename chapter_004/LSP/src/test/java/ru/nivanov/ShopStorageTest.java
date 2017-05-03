package ru.nivanov;

import org.junit.Test;
import ru.nivanov.foods.GeneralFood;
import ru.nivanov.foods.MilkFood;
import ru.nivanov.foods.VegetableFood;
import ru.nivanov.storages.ShopStorage;

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
        GeneralFood foodTwo = new VegetableFood("parrot", TEN, "02.01.2017", "22.03.2017");
        GeneralFood[] foods = {foodOne, foodTwo};
        underTest.addFoodItem(foodOne);
        underTest.addFoodItem(foodTwo);
        GeneralFood[] expected = {underTest.getAllFoodFromStorage().get(0), underTest.getAllFoodFromStorage().get(1)};
        assertThat(foods, is(expected));

    }

    /**
     * Test for food freshness.
     */
    @Test
    public void whenCheckConditionThenReturnResult() {
        GeneralFood foodOne = new MilkFood("milk", TEN, "02.02.2017", "20.03.2017");
        GeneralFood foodTwo = new VegetableFood("parrot", TEN, "02.01.2017", "19.03.2017");
        GeneralFood foodThree = new VegetableFood("onion", TEN, "02.01.2017", "12.02.2017");
        boolean resultOne = underTest.checkCondition(foodOne, "05.03.2017");
        boolean resultTwo = underTest.checkCondition(foodTwo, "05.03.2017");
        boolean resultThree = underTest.checkCondition(foodThree, "05.03.2017");
        boolean[] test = {resultOne, resultTwo, resultThree};
        boolean[] expected = {true, true, false};
        assertThat(test, is(expected));

    }

}