package ru.nivanov.hashAndEqualsOverriding;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 10.05.2017.
 */
public class UserHashEqualsOverTest {
    /**
     * Test for adding users when override equals and hashCode.
     */
    @Test
    public void whenAddUsersWithOverridedEqualsAndHash() {
        UserHashEqualsOver userHashEqualsOverOne = new UserHashEqualsOver("Ann", 1,
                new GregorianCalendar(1998, 12, 10));
        UserHashEqualsOver userHashEqualsOverTwo = new UserHashEqualsOver("Ann", 1,
                new GregorianCalendar(1998, 12, 10));

        Map<UserHashEqualsOver, Object> userObjectMap = new HashMap<>();
        userObjectMap.put(userHashEqualsOverOne, "first");
        userObjectMap.put(userHashEqualsOverTwo, "second");

        System.out.println(userObjectMap);

        assertThat(userObjectMap.size(), is(1));

    }


}