package ru.nivanov;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 17.04.2017.
 */
public class SortUserTest {

    /**
     * Test for sorting by age.
     */
    @Test
    public void whenSortByAgeThenReturnResult() {
        final int tri = 3;
        User one = new User("Ann", tri);
        User two = new User("Mike", 2);
        User three = new User("John", 1);
        LinkedList<User> usersList = new LinkedList<>();
        usersList.add(one);
        usersList.add(three);
        usersList.add(two);
        Set<User> result = new SortUser().sort(usersList);
        int[] expected = {1, 2, tri};
        Iterator<User> iterator = result.iterator();
        int[] resultAges = new int[result.size()];
        int count = 0;
        while (iterator.hasNext()) {
            resultAges[count] = iterator.next().getAge();
            count++;
        }
        assertThat(expected, is(resultAges));
    }

}