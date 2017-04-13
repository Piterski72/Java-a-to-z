package ru.nivanov;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 13.04.2017.
 */
public class UserConvertTest {

    /**
     * Test for converting list to map.
     */
    @Test
    public void whenConvertSuccessThenReturnResult() {
        User oneUser = new User(2, "Alex", "Zero");
        User twoUser = new User(1, "Mike", "SinSity");
        final int three = 3;
        User threeUser = new User(three, "John", "Pindostansk");

        List<User> userList = new LinkedList<>();
        userList.add(oneUser);
        userList.add(twoUser);
        userList.add(threeUser);

        UserConvert underTest = new UserConvert();
        HashMap<Integer, User> userHashMap = underTest.process(userList);
        boolean[] result = {userHashMap.containsKey(1), userHashMap.containsKey(2), userHashMap.containsKey(0)};
        boolean[] expected = {true, true, false};

        assertThat(result, is(expected));


    }

}