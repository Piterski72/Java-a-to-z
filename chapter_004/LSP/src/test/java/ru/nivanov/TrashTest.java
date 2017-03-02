package ru.nivanov;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 02.03.2017.
 */
public class TrashTest {
    private static final int TEN = 10;
    private static final int STOODIN = 101;
    private static final int STO = 100;
    private Trash underTest = new Trash();

    /**
     * Test for ad2 item.
     */
    @Test
    public void whenAddFoodItem2ThenReturnResult() {
        Food foodOne = new MilkFood("milk", TEN, "02.03.2017", "22.03.2017");
        Food foodTwo = new VegetableFood("parrot", TEN, "02.03.2016", "22.12.2016");
        Food[] foods = {foodOne, foodTwo};
        underTest.addFoodItem(foodOne);
        underTest.addFoodItem(foodTwo);
        Food[] expected = {underTest.getTrashFoods().get(0), underTest.getTrashFoods().get(1)};
        assertThat(foods, is(expected));
    }

    /**
     * Test for remove item.
     */
    @Test
    public void whenRemoveFoodItemThenReturnResult() {
        Food foodOne = new MilkFood("milk", TEN, "02.03.2017", "22.03.2013");
        Food foodTwo = new MilkFood("milk", TEN, "02.03.2017", "22.03.2013");
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
        boolean expectedFalse = underTest.checkCondition(STO);
        boolean expectedTrue = underTest.checkCondition(STOODIN);
        boolean[] test = {expectedFalse, expectedTrue};
        boolean[] expected = {false, true};
        assertThat(test, is(expected));
    }

}