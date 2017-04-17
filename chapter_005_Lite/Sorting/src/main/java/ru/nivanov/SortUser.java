package ru.nivanov;

import java.util.*;

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

    /**
     * Sorting by hash code.
     * @param users ..
     * @return sorted list.
     */
    public List<User> sortHash(List<User> users) {
        List<User> sortedByHashCode = new LinkedList<>();
        sortedByHashCode.addAll(users);
        sortedByHashCode.sort(new HashCompare());
        return sortedByHashCode;
    }

    /**
     * Sorting by name length.
     * @param users ..
     * @return sorted list.
     */
    public List<User> sortLength(List<User> users) {
        List<User> sortedByNameLength = new LinkedList<>();
        sortedByNameLength.addAll(users);
        sortedByNameLength.sort(new NameLengthCompare());
        return sortedByNameLength;
    }

    /**
     * Hash compare inner class.
     */
    class HashCompare implements Comparator<User> {
        /**
         * Compares its two arguments for order.  Returns a negative integer,
         * zero, or a positive integer as the first argument is less than, equal
         * to, or greater than the second.
         * @param userOne the first object to be compared.
         * @param userTwo the second object to be compared.
         * @return a negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         * @throws NullPointerException if an argument is null and this
         * comparator does not permit null arguments
         * @throws ClassCastException if the arguments' types prevent them from
         * being compared by this comparator.
         */
        @Override
        public int compare(User userOne, User userTwo) {
            return Integer.valueOf(userOne.hashCode()).compareTo(userTwo.hashCode());
        }

    }

    /**
     * Name length compare inner class.
     */
    class NameLengthCompare implements Comparator<User> {
        /**
         * Compares its two arguments for order.  Returns a negative integer,
         * zero, or a positive integer as the first argument is less than, equal
         * to, or greater than the second.<p>
         * @param userOne the first object to be compared.
         * @param userTwo the second object to be compared.
         * @return a negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         * @throws NullPointerException if an argument is null and this
         * comparator does not permit null arguments
         * @throws ClassCastException if the arguments' types prevent them from
         * being compared by this comparator.
         */
        @Override
        public int compare(User userOne, User userTwo) {
            return Integer.valueOf(userOne.getName().length()).compareTo(userTwo.getName().length());
        }
    }


}
