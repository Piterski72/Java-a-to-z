package ru.nivanov;

import java.util.Comparator;
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
    Set<User> sort(List<User> users) {
        TreeSet<User> sortedUsersByAge = new TreeSet<>();
        sortedUsersByAge.addAll(users);
        return sortedUsersByAge;
    }

    /**
     * Sorting by hash code.
     * @param users ..
     * @return sorted list.
     */
    List<User> sortHash(List<User> users) {
        users.sort((userOne, userTwo) -> Integer.valueOf(userOne.hashCode()).compareTo(userTwo.hashCode()));

        return users;
    }

    /**
     * Sorting by name length.
     * @param users ..
     * @return sorted list.
     */
    List<User> sortLength(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User userOne, User userTwo) {
                return Integer.valueOf(userOne.getName().length()).compareTo(userTwo.getName().length());
            }
        });
        return users;
    }

}
