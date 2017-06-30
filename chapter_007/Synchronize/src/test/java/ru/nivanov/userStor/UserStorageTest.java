package ru.nivanov.userStor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Nikolay Ivanov on 30.06.2017.
 */
public class UserStorageTest {

    private final int tri = 3;

    private Map<Integer, User> userMap;
    private UserStorage underTest;
    private User one;
    private User two;
    private User three;

    /**
     * Preparing ..
     */
    @Before
    public void setUp() {
        one = new User("Ann", 1);
        two = new User("Joshua", 2);
        three = new User("Mike", tri);
        userMap = new HashMap<>();
        underTest = new UserStorage(userMap);
        underTest.add(one);
        underTest.add(two);
        underTest.add(three);
    }

    /**
     * Test for adding user.
     */
    @Test
    public void whenAddUserThenReturnResult() {
        User result = underTest.search(2);
        User expected = two;

        assertThat(result, is(expected));

    }

    /**
     * Test for update method.
     */
    @Test
    public void whenUpdateThenReturnResult() {
        three.setAccount(1);
        underTest.update(tri, three);

        int result = underTest.search(tri).getAccount();
        int expected = 1;

        assertThat(result, is(expected));
    }

    /**
     * Test for delete method.
     */
    @Test
    public void whenDeleteThenReturnResult() {
        underTest.delete(tri);

        User result = underTest.search(tri);

        Assert.assertNull(result);
    }

    /**
     * Test for transfer method.
     */
    @Test
    public void whenTransferThenReturnResult() {
        one.setAccount(2);
        underTest.update(1, one);
        underTest.transfer(1, 2, 2);

        int[] results = {underTest.search(1).getAccount(), underTest.search(2).getAccount()};
        int[] expected = {0, 2};

        assertThat(results, is(expected));

    }

}