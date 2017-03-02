package ru.nivanov;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 02.03.2017.
 */
public class ShopTest {
    private static final int TEN = 10;
    private static final int STOODIN = 101;
    private static final int TWENTYSIX = 26;
    private Shop underTest = new Shop();

    /**
     * Test for adding items.
     */
    @Test
    public void whenAddFoodItemThenReturnResult() {
        Food foodOne = new MilkFood("milk", TEN, "22.02.2017", "20.03.2017");
        Food foodTwo = new VegetableFood("parrot", TEN, "02.01.2017", "12.03.2017");
        Food[] foods = {foodOne, foodTwo};
        underTest.addFoodItem(foodOne);
        underTest.addFoodItem(foodTwo);
        Food[] expected = {underTest.getShopFoods().get(0), underTest.getShopFoods().get(1)};
        assertThat(foods, is(expected));

    }

    /**
     * Test for food freshness.
     */
    @Test
    public void whenCheckConditionThenReturnResult() {
        boolean resultOne = underTest.checkCondition(0);
        boolean resultTwo = underTest.checkCondition(TWENTYSIX);
        boolean resultThree = underTest.checkCondition(STOODIN);
        boolean[] test = {resultOne, resultTwo, resultThree};
        boolean[] expected = {false, true, false};
        assertThat(test, is(expected));

    }

}