package ru.nivanov;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Nikolay Ivanov on 17.04.2017.
 */
public class SortUserTest {
    private final int tri = 3;
    private final User one = new User("Ann", tri);
    private final User two = new User("Mike", 2);
    private final User three = new User("Joshua", 1);

    /**
     * Test for sorting by age.
     */
    @Test
    public void whenSortByAgeThenReturnResult() {
        LinkedList<User> usersList = new LinkedList<>();
        usersList.add(this.one);
        usersList.add(this.three);
        usersList.add(this.two);
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

    /**
     * Test for sorting by hash.
     */
    @Test
    public void whenSortedByHachThenReturnResult() {
        LinkedList<User> usersByHash = new LinkedList<>();
        usersByHash.add(this.one);
        usersByHash.add(this.three);
        usersByHash.add(this.two);
        List<User> result = new SortUser().sortHash(usersByHash);
        Iterator<User> iterator = result.iterator();
        int[] resultHashes = new int[result.size()];
        int count = 0;
        while (iterator.hasNext()) {
            resultHashes[count] = iterator.next().hashCode();
            count++;
        }
        assertTrue(resultHashes[0] < resultHashes[1] && resultHashes[1] < resultHashes[2]);
    }

    /**
     * Test for sorting by name length.
     */
    @Test
    public void whenSortedByNameLengthThenReturnResult() {
        LinkedList<User> usersByNameLength = new LinkedList<>();
        usersByNameLength.add(this.three);
        usersByNameLength.add(this.two);
        usersByNameLength.add(this.one);
        List<User> result = new SortUser().sortLength(usersByNameLength);
        Iterator<User> iterator = result.iterator();
        int[] resultNamesLength = new int[result.size()];
        int count = 0;
        while (iterator.hasNext()) {
            resultNamesLength[count] = iterator.next().getName().length();
            count++;
        }
        assertTrue(resultNamesLength[0] < resultNamesLength[1] && resultNamesLength[1] < resultNamesLength[2]);


    }

}