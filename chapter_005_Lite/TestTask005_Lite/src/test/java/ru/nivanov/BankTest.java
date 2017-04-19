package ru.nivanov;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 18.04.2017.
 */
public class BankTest {
    private final double fiftyFive = 55;
    private final double forty = 40;
    private final int sevenSevenSeven = 777;
    private Bank underTestBank;
    private User one;
    private User two;
    private User three;
    private User four;

    /**
     *
     */
    @Before
    public void setUp() {
        final int passOne = 123;
        final int passTwo = 234;
        final int passThree = 345;
        final int passFour = 456;
        one = new User("Ann", passOne);
        two = new User("Mike", passTwo);
        three = new User("Joshua", passThree);
        four = new User("Ann", passFour);

        Map<User, List<Account>> userListMap = new TreeMap<>();
        underTestBank = new Bank(userListMap);
        underTestBank.addUser(one);
        underTestBank.addUser(two);
        underTestBank.addUser(three);
        underTestBank.addUser(four);
    }

    /**
     * Test for adding user to bank.
     */
    @Test
    public void whenAddUserThenReturnResult() {
        setUp();
        final int expected = 4;
        Set<User> userList = underTestBank.getUserListMap().keySet();
        assertThat(userList.size(), is(expected));
    }

    /**
     * Test for removing user from bank.
     */
    @Test
    public void whenDeleteUserWithNonEmptyAccountThenSkipUserAndReturnResult() {
        setUp();
        final int result = 3;
        underTestBank.addAccountToUser(one, new Account(fiftyFive, sevenSevenSeven));
        underTestBank.deleteUser(one);
        underTestBank.deleteUser(four);
        assertThat(underTestBank.getUserListMap().size(), is(result));

    }

    /**
     * Test for adding account to user.
     */
    @Test
    public void whenAddAccountToUserThenReturnResult() {
        setUp();
        underTestBank.addAccountToUser(one, new Account(fiftyFive, sevenSevenSeven));
        List<Account> userAccounts = underTestBank.getUserAccounts(one);
        Iterator<Account> iter = userAccounts.iterator();
        boolean added = false;
        while (iter.hasNext()) {
            Account current = iter.next();
            if (current.getValue() == fiftyFive) {
                added = true;
            }
        }
        assertThat(added, is(true));


    }

    /**
     * Test for deleting account from user.
     */
    @Test
    public void whenDeleteAccountFromUserThenReturnResult() {
        setUp();
        Account account = new Account(0, sevenSevenSeven);
        underTestBank.addAccountToUser(one, account);
        List<Account> userAccountsBefore = underTestBank.getUserAccounts(one);
        int before = userAccountsBefore.size();
        underTestBank.deleteAccountFromUser(one, account);
        List<Account> userAccountsAfter = underTestBank.getUserAccounts(one);
        int after = userAccountsAfter.size();
        assertThat((before - after), is(1));

    }

    /**
     * Test for getting user accounts.
     */
    @Test
    public void whenGetUserAccountsThenReturnResult() {
        setUp();
        Account account = new Account(0, sevenSevenSeven);
        underTestBank.addAccountToUser(one, account);
        List<Account> userAccountsAfter = underTestBank.getUserAccounts(one);
        int after = userAccountsAfter.size();
        assertThat(after, is(2));
    }

    /**
     * Test for correctin money transfer.
     */
    @Test
    public void whenTransferMoneyThenReturnResult() {
        setUp();
        Account accountOne = new Account(fiftyFive, sevenSevenSeven);
        underTestBank.addAccountToUser(one, accountOne);
        Account accountTwo = new Account(0, 2);
        underTestBank.addAccountToUser(two, accountTwo);
        underTestBank.transferMoney(one, accountOne, two, accountTwo, (fiftyFive - forty));
        double[] expected = {forty, (fiftyFive - forty)};
        double[] results = {0, 0};
        List<Account> userAccountsOneAfter = underTestBank.getUserAccounts(one);
        Iterator<Account> itOne = userAccountsOneAfter.iterator();
        double resultOne = 0;
        while (itOne.hasNext()) {
            Account current = itOne.next();
            if (current.equals(accountOne)) {
                resultOne = current.getValue();
            }
        }
        results[0] = resultOne;
        List<Account> userAccountsTwoAfter = underTestBank.getUserAccounts(two);
        Iterator<Account> itTwo = userAccountsTwoAfter.iterator();
        double resultTwo = 0;
        while (itTwo.hasNext()) {
            Account current = itTwo.next();
            if (current.equals(accountTwo)) {
                resultTwo = current.getValue();
            }
        }
        results[1] = resultTwo;
        assertThat(expected, is(results));
    }

}