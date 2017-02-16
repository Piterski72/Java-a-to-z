package ru.nivanov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 16.02.2017.
 */
public class SeaAndIslesTest {
    /**
     * test for show help.
     */
    @Test
    public void whenFoundBiggestIsleThenReturnResult() {
        final int EXPECTED = 14;
        int[][] sea = {{0, 1, 0, 1, 1, 0, 0},
                       {0, 0, 0, 0, 0, 1, 0},
                       {0, 0, 0, 0, 0, 0, 0},
                       {1, 1, 1, 1, 1, 1, 1},
                       {0, 1, 1, 1, 0, 0, 1},
                       {0, 0, 1, 0, 0, 0, 1},
                       {1, 0, 0, 1, 0, 0, 0},
                       {1, 0, 1, 1, 0, 1, 0},
                       {1, 1, 0, 1, 0, 0, 1},
                       {1, 1, 1, 1, 0, 0, 1},
                       {1, 0, 1, 0, 0, 1, 1}};
        SeaAndIsles blackSea = new SeaAndIsles(sea);
        int result = blackSea.findBiggestIsle();
        assertThat(result, is(EXPECTED));
    }

}