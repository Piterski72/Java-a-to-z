package ru.nivanov;

import ru.nivanov.model.User;

import java.util.Map;

/**
 * Created by Nikolay Ivanov on 31.10.2017.
 */
public class BaseTest {

    public static void main(String[] args) {
        DbaseHandler underTest = new DbaseHandler();
        underTest.loadProps();
        underTest.connectToBase();
        Map<Integer, User> map = underTest.showUsers();

        for (Map.Entry<Integer, User> entry : map.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
        underTest.closeConnection();
    }
}
