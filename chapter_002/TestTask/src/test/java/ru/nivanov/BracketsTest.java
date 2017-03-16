package ru.nivanov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * BracketsTest class.
 * @author nivanov.
 */
public class BracketsTest {
    /**
     * Test for brackets correct position.
     */
    @Test
    public void whenBracketsCorrectThenReturnOk() {
        Brackets brackets = new Brackets("(a+b)*(45-(66+(e+f)/(x+y)-r)/13)");
        boolean test = brackets.skobkiOk();
        assertThat(test, is(true));
    }

    /**
     * Test for brackets incorrect1 position.
     */
    @Test
    public void whenBracketsInCorrectThenReturnOk() {
        Brackets brackets = new Brackets("(a+b))*(45-(66+(e+f)/(x+y)-r/13)");
        boolean test = brackets.skobkiOk();
        assertThat(test, is(false));
    }

    /**
     * Test for brackets incorrect2 position.
     */
    @Test
    public void whenBracketsInCorrect2ThenReturnOk() {
        Brackets brackets = new Brackets("(a+b)*45-(66/(e+f)-77)/x+y)-(r-6(4*7/13)");
        boolean test = brackets.skobkiOk();
        assertThat(test, is(false));
    }

    /**
     * Test for no brackets.
     */
    @Test
    public void whenNoBracketsThenReturnOk() {
        Brackets brackets = new Brackets("a+b*45-66/e+f-77/x+y-64*7/1");
        boolean test = brackets.skobkiOk();
        assertThat(test, is(false));
    }

    /**
     * Test for brackets correct position (simple).
     */
    @Test
    public void whenSimpleBracketsCorrectThenReturnOk() {
        Brackets brackets = new Brackets("(a+b)*(45-(66+(e+f)/(x+y)-r)/13)");
        boolean test = brackets.simpleCode();
        assertThat(test, is(true));
    }

    /**
     * Test for brackets incorrect1 position (simple).
     */
    @Test
    public void whenSimpleBracketsInCorrectThenReturnOk() {
        Brackets brackets = new Brackets("(a+b))*(45-(66+(e+f)/(x+y)-r/13)");
        boolean test = brackets.simpleCode();
        assertThat(test, is(false));
    }

    /**
     * Test for brackets incorrect2 position (simple).
     */
    @Test
    public void whenSimpleBracketsInCorrect2ThenReturnOk() {
        Brackets brackets = new Brackets("(a+b)*45-(66/(e+f)-77)/x+y)-(r-6(4*7/13)");
        boolean test = brackets.simpleCode();
        assertThat(test, is(false));
    }
}