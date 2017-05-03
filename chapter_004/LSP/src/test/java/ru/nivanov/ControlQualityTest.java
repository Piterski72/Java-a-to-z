package ru.nivanov;

import org.junit.Test;
import ru.nivanov.foods.GeneralFood;
import ru.nivanov.foods.MilkFood;
import ru.nivanov.start.ControlQuality;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Nikolay Ivanov on 02.03.2017.
 */
public class ControlQualityTest {
    private static final int FOUR = 4;
    private ControlQuality controlQuality;

    /**
     * Test for filling storages.
     */
    @Test
    public void whenInstallStoragesThenReturnResult() {
        controlQuality = new ControlQuality(new ArrayList<>());
        controlQuality.installMainStorages();
        int result = controlQuality.getMainStorages().size();
        assertThat(result, is(FOUR));
    }

    /**
     * Test for spreading food.
     */
    @Test
    public void whenSetFoodDestinationThenReturnResult() {
        GeneralFood mockFood = mock(MilkFood.class);
        //add food behavior
        when(mockFood.getShelfLifePercent("01.03.2017")).thenReturn((double) 1);
        when(mockFood.getName()).thenReturn("");
        ArrayList<GeneralFood> testFood = new ArrayList<>();
        testFood.add(mockFood);
        controlQuality = new ControlQuality(testFood);
        controlQuality.installMainStorages();
        //boolean expected = controlQuality.setFoodDestination();
        //assertThat(expected, is(true));
    }
}