package ru.nivanov;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Nikolay Ivanov on 02.03.2017.
 */
public class ControlQualityTest {
    private static final int THREE = 3;
    private static final double STOODIN = 101;

    private ControlQuality controlQuality;

    /**
     * Test for filling storages.
     */
    @Test
    public void whenInstallStoragesThenReturnResult() {
        controlQuality = new ControlQuality(new ArrayList<>());
        controlQuality.installStorages();
        int result = controlQuality.getStorages().size();
        assertThat(result, is(THREE));
    }

    /**
     * Test for spreading food.
     */
    @Test
    public void whenSetFoodDestinationThenReturnResult() {
        Food mockFood = mock(Food.class);
        //add food behavior
        when(mockFood.getShelfLifePercent()).thenReturn((STOODIN));
        ArrayList<Food> testFood = new ArrayList<>();
        testFood.add(mockFood);
        controlQuality = new ControlQuality(testFood);
        controlQuality.installStorages();
        boolean expected = controlQuality.setFoodDestination();
        assertThat(expected, is(true));
    }
}