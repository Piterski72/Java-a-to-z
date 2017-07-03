package ru.nivanov.userStor;

import net.jcip.annotations.ThreadSafe;

import java.util.Map;

/**
 * Created by Nikolay Ivanov on 30.06.2017.
 */
@ThreadSafe
class UserStorage {

    private final Map<Integer, User> users;

    /**
     * Constructor.
     * @param users ..
     */
    UserStorage(Map<Integer, User> users) {
        this.users = users;
    }

    /**
     * Add new user.
     * @param user ..
     */
    synchronized void add(User user) {
        this.users.put(user.getId(), user);
    }

    /**
     * Update storage with updated user.
     * @param userId ..
     * @param user ..
     * @return ..
     */
    synchronized boolean update(int userId, User user) {
        if (this.users.containsKey(userId) && user.getId() == userId) {
            this.users.put(userId, user);
            return true;
        }
        return false;
    }

    /**
     * Delete user from user storage.
     * @param userId ..
     * @return ..
     */
    synchronized boolean delete(int userId) {
        if (this.users.containsKey(userId)) {
            this.users.remove(userId);
            return true;
        }
        return false;
    }

    /**
     * Search user by id.
     * @param userId ..
     * @return ..
     */
    synchronized User search(int userId) {
        if (this.users.containsKey(userId)) {
            return this.users.get(userId);
        } else {
            return null;
        }
    }

    /**
     * Transfer amount from one user to another.
     * @param fromId ..
     * @param toId ..
     * @param amount ..
     * @return boolean result ..
     */
    synchronized boolean transfer(int fromId, int toId, int amount) {
        if (this.users.containsKey(fromId) && this.users.containsKey(toId)) {
            User fromUser = this.users.get(fromId);
            User toUser = this.users.get(toId);
            if (fromUser.getAccount() < amount) {
                System.out.println("not enough for transfer");
                return false;
            } else {
                fromUser.setAccount(fromUser.getAccount() - amount);
                toUser.setAccount(toUser.getAccount() + amount);
                return true;
            }
        }
        return false;
    }
}
