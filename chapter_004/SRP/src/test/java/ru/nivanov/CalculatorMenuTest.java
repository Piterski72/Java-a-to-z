package ru.nivanov;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by Nikolay Ivanov on 27.02.2017.
 */
public class CalculatorMenuTest {
    private final int three = 3;
    private final int four = 4;
    private final int five = 5;
    private final int six = 6;
    private final int seven = 7;
    private IO mockIO = new MockIO();
    private ICalculator calc = new Calculator();
    private CalculatorMenu calcMenu = new CalculatorMenu(this.calc, this.mockIO);
    private String sep = System.getProperty("line.separator");

    /**
     * Test for get range method.
     * @throws Exception ..
     */
    @Test
    public void whenGetRangeThenReturnResult() throws Exception {
        calcMenu.fillActions();
        int[] test = calcMenu.getRange();
        int[] expected = {0, 1, 2, three, four, five, six, seven};
        assertThat(test, is(expected));

    }

    /**
     * Test for fill actions method.
     * @throws Exception ..
     */
    @Test
    public void whenFillActionsThenReturnResult() throws Exception {
        calcMenu.fillActions();
        ArrayList<CalcActions> forTest = calcMenu.getActions();
        String expected = forTest.get(seven).info();
        String result = "Cotangens calculate";
        assertThat(result, is(expected));

    }

    /**
     * Test for show menu.
     * @throws Exception ..
     */
    @Test
    public void whenShowMenuThenReturnResult() throws Exception {
        //add the behavior of calc service to add two numbers
        String str;
        calcMenu.fillActions();
        calcMenu.showMenu();
        String test = mockIO.read();
        String expected = Joiner.on(sep).join("0.Add operation", "1.Subtract operation", "2.Multiple operation",
                "3.Divide operation", "4.Sinus calculate", "5.Cosinus calculate", "6.Tangens calculate",
                "7.Cotangens calculate") + sep;

        assertThat(test, is(expected));

    }

    /**
     * Test for select menu.
     * @throws Exception ..
     */
    @Test
    public void whenSelectMenuThenReturnResult() throws Exception {
        int[] range = {0, 1, 2, three, four, five, six, seven};
        boolean reuse = false;
        Validator mockValidator = mock(Validator.class);
        //add validator behavior
        when(mockValidator.getInt("Enter operation")).thenReturn(0);
        when(mockValidator.checkRange(0, range)).thenReturn(true);
        when(mockValidator.getDouble("Enter first arg")).thenReturn(Double.valueOf(four));
        when(mockValidator.getDouble("Enter second arg")).thenReturn(Double.valueOf(three));
        calcMenu.fillActions();
        calcMenu.selectMenu(mockValidator, reuse);
        verify(mockValidator, times(1)).getInt("Enter operation");
        verify(mockValidator, times(1)).checkRange(0, range);
        verify(mockValidator, times(1)).getDouble("Enter first arg");
        verify(mockValidator, times(1)).getDouble("Enter second arg");
        double expected = calc.getResult();
        assertThat(expected, is(Double.valueOf(seven)));
    }

}