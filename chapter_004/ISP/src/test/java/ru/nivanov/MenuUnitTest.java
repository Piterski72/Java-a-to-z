package ru.nivanov;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 16.03.2017.
 */
public class MenuUnitTest {
    private final MenuUnit underTest = new MenuUnit("0.", "UnderTest");
    private final MenuUnit underTestSub = new MenuUnit(underTest, null, "1.", "SubTest");
    private final MenuUnit underTestSubNext = new MenuUnit(underTestSub, "1.1.", "NextTest");

    /**
     * Test for getting name.
     */
    @Test
    public void whenGetNameThenReturnResult() {
        String expected = underTest.getName();
        assertThat(expected, is("UnderTest"));
    }

    /**
     * Test for getting prev unit.
     */
    @Test
    public void whenGetPrevUnitThenReturnResult() {
        MenuUnit expected = underTestSubNext.getPrevUnit();
        assertThat(expected, is(underTestSub));
    }

    /**
     * Test for getting next unit.
     */
    @Test
    public void whenGetNextUnitThenReturnResult() {
        MenuUnit expected = underTestSub.getNextUnit();
        assertThat(expected, is(underTestSubNext));
    }

    /**
     * Test for getting sub unit.
     */
    @Test
    public void whenGetSubUnitThenReturnResult() {
        MenuUnit expected = underTest.getSubUnit();
        assertThat(expected, is(underTestSub));
    }

    /**
     * Test for getting super unit.
     */
    @Test
    public void whenGetSuperUnitThenReturnResult() {
        MenuUnit expected = underTestSub.getSuperUnit();
        assertThat(expected, is(underTest));
    }

    /**
     *
     */
    @Test
    public void whenGetKeyThenbReturnResult() {
        String expected = underTest.key();
        assertThat(expected, is("0."));
    }

}