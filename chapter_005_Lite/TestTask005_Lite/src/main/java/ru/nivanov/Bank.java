package ru.nivanov;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Nikolay Ivanov on 18.04.2017.
 */
class Bank {

    private Map<User, List<Account>> userListMap;

    /**
     * Constructor.
     * @param userListMap ..
     */
    Bank(Map<User, List<Account>> userListMap) {
        this.userListMap = userListMap;
    }

    /**
     * Getter for map.
     * @return ..
     */
    Map<User, List<Account>> getUserListMap() {
        return userListMap;
    }

    /**
     * Adding user.
     * @param user ..
     */
    void addUser(User user) {
        if (this.userListMap.containsKey(user)) {
            System.out.println("User already exists");
        } else {
            List<Account> userAcounts = new ArrayList<>();
            final int thousandMinus = -1000;
            int requisite = Long.valueOf(System.currentTimeMillis()).intValue() / (thousandMinus);
            Account defaultAccount = new Account(0, requisite);
            userAcounts.add(defaultAccount);
            this.userListMap.put(user, userAcounts);
        }
    }

    /**
     * Deleting user.
     * @param user ..
     * @throws UserNotFoundException ..
     */
    void deleteUser(User user) throws UserNotFoundException {
        if (this.userListMap.containsKey(user)) {
            List<Account> accounts = getUserAccounts(user);
            boolean isEmty = true;
            for (Account val : accounts) {
                if (val.getValue() != 0) {
                    isEmty = false;
                    System.out.println(
                            String.format("Cannot delete user %s, pass %d : non-empty aacount", user.getName(),
                                    user.getPassport()));
                    break;
                }
            }
            if (isEmty) {
                this.userListMap.remove(user);
                System.out.println(String.format("User %s pass %d deleted", user.getName(), user.getPassport()));
            }
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    /**
     * Add acount to user.
     * @param user ..
     * @param account ..
     * @throws UserNotFoundException ..
     */
    void addAccountToUser(User user, Account account) throws UserNotFoundException {
        if (this.userListMap.containsKey(user)) {
            List<Account> userAcount = getUserAccounts(user);
            userAcount.add(account);
            this.userListMap.put(user, userAcount);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    /**
     * Delete acount from user.
     * @param user ..
     * @param account ..
     * @throws UserNotFoundException ..
     */
    void deleteAccountFromUser(User user, Account account) throws UserNotFoundException {
        if (this.userListMap.containsKey(user)) {
            List<Account> userAccounts = getUserAccounts(user);
            Iterator<Account> iter = userAccounts.iterator();
            while (iter.hasNext()) {
                Account current = iter.next();
                if (current.getValue() == 0) {
                    iter.remove();
                    break;
                } else {
                    System.out.println("Account not empty or not found");
                }
            }
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    /**
     * Get all user acounts.
     * @param user ..
     * @return ..
     * @throws UserNotFoundException ..
     */
    List<Account> getUserAccounts(User user) throws UserNotFoundException {
        if (this.userListMap.containsKey(user)) {
            return this.userListMap.get(user);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    /**
     * Method for transferring money from source to destination.
     * @param srcUser ..
     * @param srcAccount ..
     * @param dstUser ..
     * @param dstAccount ..
     * @param amount ..
     * @return ..
     */
    boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean tranferComplete = false;
        if (this.userListMap.containsKey(srcUser) && this.userListMap.containsKey(dstUser)) {
            List<Account> userSourceAcount = getUserAccounts(srcUser);
            List<Account> userDestAcount = getUserAccounts(dstUser);
            for (Account source : userSourceAcount) {
                for (Account dest : userDestAcount) {
                    if (source.equals(srcAccount) && source.getValue() > amount && dest.equals(dstAccount)) {
                        source.setValue(source.getValue() - amount);
                        dest.setValue(dest.getValue() + amount);
                        tranferComplete = true;
                        break;
                    }
                }
            }
        } else {
            throw new UserNotFoundException("user source/dest not found");
        }
        return tranferComplete;
    }
}
