package ru.nivanov;

import org.junit.Test;
import ru.nivanov.Foods.GeneralFood;
import ru.nivanov.Foods.MilkFood;
import ru.nivanov.Start.ControlQuality;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Nikolay Ivanov on 02.03.2017.
 */
public class ControlQualityTest {
    private static final int SIX = 6;
    private ControlQuality controlQuality;

    /**
     * Test for filling storages.
     */
    @Test
    public void whenInstallStoragesThenReturnResult() {
        controlQuality = new ControlQuality(new ArrayList<>());
        controlQuality.installStorages();
        int result = controlQuality.getStorages().size();
        assertThat(result, is(SIX));
    }

    /**
     * Test for spreading food.
     */
    @Test
    public void whenSetFoodDestinationThenReturnResult() {
        GeneralFood mockFood = mock(MilkFood.class);
        //add food behavior
        when(mockFood.getShelfLifePercent()).thenReturn((double) 1);
        when(mockFood.getName()).thenReturn("");
        ArrayList<GeneralFood> testFood = new ArrayList<>();
        testFood.add(mockFood);
        controlQuality = new ControlQuality(testFood);
        controlQuality.installStorages();
        boolean expected = controlQuality.setFoodDestination();
        assertThat(expected, is(true));
    }
}