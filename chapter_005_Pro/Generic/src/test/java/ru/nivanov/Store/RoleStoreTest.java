package ru.nivanov.Store;

import org.junit.Test;
import ru.nivanov.SimpleArray;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 02.05.2017.
 */
public class RoleStoreTest {
    private final Role roleOne = new Role("killEmAll");
    private final Role roleTwo = new Role("noRemorse");
    private final Role roleThree = new Role("whiplash");
    private final int three = 3;

    /**
     * Test for add user.
     */
    @Test
    public void whenAddUserThenReturnResult() {

        SimpleArray<Role> roleSimpleArray = new SimpleArray<>(new Role[three]);
        RoleStore underTest = new RoleStore(roleSimpleArray);
        underTest.add(roleOne);
        underTest.add(roleTwo);

        int result = underTest.findObjectIndexById("noRemorse");
        assertThat(result, is(1));

    }

    /**
     * Test for delete user.
     */
    @Test
    public void whenDeleteUserThenReturnResult() {

        SimpleArray<Role> roleSimpleArray = new SimpleArray<>(new Role[three]);
        RoleStore underTest = new RoleStore(roleSimpleArray);
        underTest.add(roleOne);
        underTest.add(roleTwo);
        underTest.add(roleThree);

        underTest.delete("killEmAll");

        int result = underTest.findObjectIndexById("killEmAll");
        assertThat(result, is(-1));

    }

    /**
     * Test for update user.
     */
    @Test
    public void whenUpdateUserThenReturnResult() {

        SimpleArray<Role> roleSimpleArray = new SimpleArray<>(new Role[three]);
        RoleStore underTest = new RoleStore(roleSimpleArray);
        underTest.add(roleOne);
        underTest.add(roleTwo);
        underTest.add(roleThree);

        underTest.update(roleThree, new Role("seek and destroy"));

        int result = underTest.findObjectIndexById("seek and destroy");
        assertThat(result, is(2));

    }
}