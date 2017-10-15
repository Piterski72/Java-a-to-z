package ru.nivanov.base;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Nikolay Ivanov on 14.10.2017.
 */
public class DBaseManagerTest {

    /**
     * Test for getting last visit record.
     * @throws Exception ..
     */
    @Test
    public void getLastVisit() throws Exception {

        final long testVal = 11L;

        DBaseManager underTest = mock(DBaseManager.class);
        when(underTest.getLastVisit()).thenReturn(testVal);
        assertThat(underTest.getLastVisit(), is(testVal));


    }


}