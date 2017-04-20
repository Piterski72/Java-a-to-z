package ru.nivanov;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Nikolay Ivanov on 20.04.2017.
 */
public class UserTest {
    private User one;
    private User two;
    private User three;
    private User four;

    /**
     *
     */
    @Before
    public void setUp() {
        one = new User("Ann", 1);
        two = new User("Boo", 2);
        three = new User("Ann", 1);
        four = new User("Mike", 2);
    }

    /**
     * Test for name getter.
     */
    @Test
    public void whenGetNameThenReturnResult() {
        setUp();
        assertThat(one.getName(), is("Ann"));

    }

    /**
     * Tesr for pass getter.
     */
    @Test
    public void whenGetPassportThenReturnResult() {
        setUp();
        assertThat(one.getPassport(), is(1));

    }

    /**
     * Test for equals method.
     */
    @Test
    public void whenEqualsThenReturnResult() {
        setUp();
        assertThat(one.equals(three), is(true));

    }

    /**
     * Test for hashcode.
     */
    @Test
    public void whenGetHashCodeThenReturnResult() {
        setUp();
        int expected = three.hashCode();
        assertThat(one.hashCode(), is(expected));

    }

    /**
     * Test for compareTo method.
     */
    @Test
    public void whenCompareToThenReturnResult() {
        setUp();
        assertTrue(one.compareTo(two) < 0 && two.compareTo(four) < 0);

    }

}