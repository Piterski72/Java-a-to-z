package ru.nivanov;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 14.03.2017.
 */
public class MenuRunner {
    private static final ArrayList<MenuUnit> MENU_UNITS = new ArrayList<>();

    /**
     * main method.
     * @param args ..
     */
    public static void main(String[] args) {
        MenuUnit root = new MenuUnit("0", "root");
        MenuUnit one = new MenuUnit(root, null, "1.", "Task");
        MenuUnit two = new MenuUnit(one, "2.", "Task");
        MenuUnit oneOne = new MenuUnit(one, null, "1.1.", "Task");
        MenuUnit oneTwo = new MenuUnit(oneOne, "1.2.", "Task");
        MenuUnit oneOneOne = new MenuUnit(oneOne, null, "1.1.1.", "Task");
        MenuUnit oneOneTwo = new MenuUnit(oneOneOne, "1.1.2.", "Task");
        MenuUnit oneTwoOne = new MenuUnit(oneTwo, null, "1.2.1.", "Task");
        MenuUnit oneTwoTwo = new MenuUnit(oneTwoOne, "1.2.2.", "Task");
        MenuUnit twoOne = new MenuUnit(two, null, "2.1.", "Task");

        MENU_UNITS.add(one);
        MENU_UNITS.add(two);
        MENU_UNITS.add(oneOne);
        MENU_UNITS.add(oneTwo);
        MENU_UNITS.add(oneOneOne);
        MENU_UNITS.add(oneOneTwo);
        MENU_UNITS.add(oneTwoOne);
        MENU_UNITS.add(oneTwoTwo);
        MENU_UNITS.add(twoOne);

        Menu menu = new Menu();
        menu.show(root);
        select("1.1.");
    }

    /**
     * select menu item.
     * @param input ..
     */
    private static void select(String input) {
        for (MenuUnit value : MENU_UNITS) {
            if (input.equals(value.key())) {
                value.execute();
            }
        }

    }

}