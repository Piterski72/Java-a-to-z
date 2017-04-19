package ru.nivanov;

import java.util.*;

/**
 * Created by Nikolay Ivanov on 18.04.2017.
 */
class Bank {
    private final int thousandMinus = -1000;

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
    public Map<User, List<Account>> getUserListMap() {
        return userListMap;
    }

    /**
     * Adding user.
     * @param user ..
     */
    public void addUser(User user) {
        List<Account> userAcounts = new ArrayList<>();
        int requisite = Long.valueOf(System.currentTimeMillis()).intValue() / (thousandMinus);
        Account defaultAccount = new Account(0, requisite);
        userAcounts.add(defaultAccount);
        this.userListMap.put(user, userAcounts);
    }

    /**
     * Deleting user.
     * @param user ..
     */
    public void deleteUser(User user) {


        Set<User> userSet = this.userListMap.keySet();
        Iterator<User> userIterator = userSet.iterator();
        while (userIterator.hasNext()) {
            User currentUser = userIterator.next();
            if (currentUser.equals(user)) {
                List<Account> accounts = getUserAccounts(user);
                boolean isEmty = true;
                for (Account val : accounts) {
                    if (val.getValue() != 0) {
                        isEmty = false;
                        System.out.println(
                                String.format("Cannot delete user %s, pass %d : non-empty aacount", user.getName(),
                                        user.getPassport()));
                    }
                }
                if (isEmty) {
                    this.userListMap.remove(user);
                    System.out.println(String.format("User %s pass %d deleted", user.getName(), user.getPassport()));
                    break;
                }
            }
        }
    }

    /**
     * Add acount to user.
     * @param user ..
     * @param account ..
     */
    public void addAccountToUser(User user, Account account) {
        List<Account> userAcount = getUserAccounts(user);
        userAcount.add(account);
        this.userListMap.put(user, userAcount);

    }

    /**
     * Delete acount from user.
     * @param user ..
     * @param account ..
     */
    public void deleteAccountFromUser(User user, Account account) {
        List<Account> userAcount = getUserAccounts(user);
        Iterator<Account> iterator = userAcount.iterator();
        while (iterator.hasNext()) {
            Account currentAcount = iterator.next();
            if (currentAcount.equals(account) && account.getValue() == 0) {
                iterator.remove();
            } else if (currentAcount.equals(account) && account.getValue() != 0) {
                System.out.println(String.format("Account not empty, value = %.2f", account.getValue()));
                System.out.println("Need to be epty to delete");
            }
        }
        this.userListMap.put(user, userAcount);
    }

    /**
     * Get all user acounts.
     * @param user ..
     * @return ..
     */
    public List<Account> getUserAccounts(User user) {
        return this.userListMap.get(user);

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
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean tranferFromSourceComplete = false;
        boolean tranferToDestComplete = false;

        List<Account> userSourceAcount = getUserAccounts(srcUser);
        Iterator<Account> srcIterator = userSourceAcount.iterator();
        while (srcIterator.hasNext()) {
            Account currentSourceAcount = srcIterator.next();
            if (currentSourceAcount.equals(srcAccount) && currentSourceAcount.getValue() > amount) {
                currentSourceAcount.setValue((currentSourceAcount.getValue() - amount));
                tranferFromSourceComplete = true;
                break;
            }
        }
        this.userListMap.put(srcUser, userSourceAcount);

        List<Account> userDestAcount = getUserAccounts(dstUser);
        Iterator<Account> dstIterator = userDestAcount.iterator();
        while (dstIterator.hasNext()) {
            Account currentDestAcount = dstIterator.next();
            if (currentDestAcount.equals(dstAccount)) {
                currentDestAcount.setValue((currentDestAcount.getValue() + amount));
                tranferToDestComplete = true;
                break;
            }
        }
        this.userListMap.put(dstUser, userDestAcount);

        return (tranferFromSourceComplete && tranferToDestComplete);
    }
}
