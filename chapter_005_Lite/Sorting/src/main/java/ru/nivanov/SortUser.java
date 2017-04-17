package ru.nivanov;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Nikolay Ivanov on 17.04.2017.
 */
class SortUser {
    /**
     * Sorting users by age.
     * @param users ..
     * @return sorted list by users age.
     */
    public Set<User> sort(List<User> users) {
        TreeSet<User> sortedUsersByAge = new TreeSet<>();
        sortedUsersByAge.addAll(users);
        return sortedUsersByAge;

    }
}
