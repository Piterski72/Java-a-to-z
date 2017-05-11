package ru.nivanov.equalsOverriding;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 10.05.2017.
 */
public class UserEqualsOverTest {
    /**
     * Test for adding users when override equals and no override to hashCode.
     */
    @Test
    public void whenAddUsersWithOverridedEquals() {
        UserEqualsOver userEqualsOverOne = new UserEqualsOver("Ann", 1, new GregorianCalendar(1998, 12, 10));
        UserEqualsOver userEqualsOverTwo = new UserEqualsOver("Ann", 1, new GregorianCalendar(1998, 12, 10));

        Map<UserEqualsOver, Object> userObjectMap = new HashMap<>();
        userObjectMap.put(userEqualsOverOne, "first");
        userObjectMap.put(userEqualsOverTwo, "second");

        System.out.println(userObjectMap);

        assertThat(userObjectMap.size(), is(2));

    }
}