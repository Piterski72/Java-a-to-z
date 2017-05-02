package ru.nivanov.Store;

import org.junit.Test;
import ru.nivanov.SimpleArray;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 02.05.2017.
 */
public class UserStoreTest {
    private final User oneUser = new User("123");
    private final User twoUser = new User("456");
    private final User threeUser = new User("test");
    private final int three = 3;

    /**
     * Test for add user.
     */
    @Test
    public void whenAddUserThenReturnResult() {

        SimpleArray<User> userSimpleArray = new SimpleArray<>(new User[three]);
        UserStore underTest = new UserStore(userSimpleArray);
        underTest.add(oneUser);
        underTest.add(twoUser);

        int result = underTest.findObjectIndexById("456");
        assertThat(result, is(1));

    }

    /**
     * Test for delete user.
     */
    @Test
    public void whenDeleteUserThenReturnResult() {

        SimpleArray<User> userSimpleArray = new SimpleArray<>(new User[three]);
        UserStore underTest = new UserStore(userSimpleArray);
        underTest.add(oneUser);
        underTest.add(twoUser);
        underTest.add(threeUser);

        underTest.delete("test");

        int result = underTest.findObjectIndexById("test");
        assertThat(result, is(-1));

    }

    /**
     * Test for update user.
     */
    @Test
    public void whenUpdateUserThenReturnResult() {

        SimpleArray<User> userSimpleArray = new SimpleArray<>(new User[three]);
        UserStore underTest = new UserStore(userSimpleArray);
        underTest.add(oneUser);
        underTest.add(twoUser);
        underTest.add(threeUser);

        underTest.update(threeUser, new User("testString"));

        int result = underTest.findObjectIndexById("testString");
        assertThat(result, is(2));

    }
}