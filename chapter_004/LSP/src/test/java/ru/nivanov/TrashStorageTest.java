package ru.nivanov;

import org.junit.Test;
import ru.nivanov.Foods.GeneralFood;
import ru.nivanov.Foods.MilkFood;
import ru.nivanov.Foods.VegetableFood;
import ru.nivanov.Storages.TrashStorage;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 02.03.2017.
 */
public class TrashStorageTest {
    private static final int TEN = 10;
    private static final int STOODIN = 101;
    private static final int STO = 100;
    private TrashStorage underTest = new TrashStorage();

    /**
     * Test for ad2 item.
     */
    @Test
    public void whenAddFoodItem2ThenReturnResult() {
        GeneralFood foodOne = new MilkFood("milk", TEN, "02.03.2017", "22.03.2017");
        GeneralFood foodTwo = new VegetableFood("parrot", TEN, "02.03.2016", "22.12.2016");
        GeneralFood[] foods = {foodOne, foodTwo};
        underTest.addFoodItem(foodOne);
        underTest.addFoodItem(foodTwo);
        GeneralFood[] expected = {underTest.getTrashFoods().get(0), underTest.getTrashFoods().get(1)};
        assertThat(foods, is(expected));
    }

    /**
     * Test for remove item.
     */
    @Test
    public void whenRemoveFoodItemThenReturnResult() {
        GeneralFood foodOne = new MilkFood("milk", TEN, "02.03.2017", "22.03.2017");
        GeneralFood foodTwo = new MilkFood("milk", TEN, "02.03.2017", "22.03.2017");
        underTest.addFoodItem(foodOne);
        underTest.addFoodItem(foodTwo);
        underTest.removeFoodItem(foodOne);
        int expected = underTest.getTrashFoods().size();
        assertThat(expected, is(1));
    }

    /**
     * Test for check food condition.
     */
    @Test
    public void whenCheckConditionThenReturnResult() {
        GeneralFood foodOne = new MilkFood("milk", TEN, "02.03.2017", "22.03.2017");
        GeneralFood foodTwo = new MilkFood("milk", TEN, "02.01.2017", "22.02.2017");
        boolean expectedFalse = underTest.checkCondition(foodOne);
        boolean expectedTrue = underTest.checkCondition(foodTwo);
        boolean[] test = {expectedFalse, expectedTrue};
        boolean[] expected = {false, true};
        assertThat(test, is(expected));
    }

}