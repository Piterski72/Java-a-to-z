package ru.nivanov.apartments;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 15.05.2017.
 */
public class ApartmentOwnersTest {
    private final int three = 3;
    private final int seven = 7;

    /**
     * Test for insert and get methods.
     */
    @Test
    public void whenInsertThenReturnResult() {
        ApartmentOwners<Integer, String> underTest = new ApartmentOwners<>();
        underTest.insert(seven, "Ivanov");
        underTest.insert(three, "Petrov");
        underTest.insert(2, "Sidorov");
        underTest.insert(three, "test");
        underTest.delete(7);

        String[] results = {underTest.get(2), underTest.get(three), underTest.get(seven), underTest.get(1)};
        String[] expected = {"Sidorov", "test", null, null};

        assertThat(results, is(expected));

    }

    /**
     * Test for iterator methods.
     */
    @Test
    public void whenHasNextThenReturnResult() {
        ApartmentOwners<Integer, String> underTest = new ApartmentOwners<>();
        underTest.insert(1, "one");
        underTest.insert(2, "two");
        underTest.insert(three, "three");

        boolean resOne = underTest.hasNext();
        underTest.next();
        boolean resTwo = underTest.hasNext();
        underTest.next();
        boolean resThree = underTest.hasNext();
        underTest.next();
        boolean resFour = underTest.hasNext();

        boolean[] results = {resOne, resTwo, resThree, resFour};
        boolean[] expected = {true, true, true, false};

        assertThat(results, is(expected));

    }


}