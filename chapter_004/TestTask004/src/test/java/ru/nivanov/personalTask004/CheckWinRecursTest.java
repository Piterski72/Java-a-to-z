package ru.nivanov.personalTask004;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 03.04.2017.
 */
public class CheckWinRecursTest {
    /**
     * test for checking if win conditions is working properly.
     */
    @Test
    public void whenWinConditionTrueThenReturnResult() {
        String[] rowOne = {"O", "X", "O"};
        String[] rowTwo = {"X", "O", "X"};
        String[] rowThree = {"X", "X", "X"};

        String[][] field = {rowOne, rowTwo, rowThree};

        CheckWinRecurs underTest = new CheckWinRecurs(field);
        boolean result = underTest.winCombination();
        assertThat(result, is(true));
    }

    /**
     * test for checking if win conditions is working properly.
     */
    @Test
    public void whenWinConditionFalseThenReturnResult() {
        String[] rowOne = {"O", "X", "O"};
        String[] rowTwo = {"X", "O", "X"};
        String[] rowThree = {"X", "O", "X"};

        String[][] field = {rowOne, rowTwo, rowThree};

        CheckWinRecurs underTest = new CheckWinRecurs(field);
        boolean result = underTest.winCombination();
        assertThat(result, is(false));
    }


}