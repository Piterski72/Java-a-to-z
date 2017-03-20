package ru.nivanov;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 20.03.2017.
 */
public class MenuRunnerSimple {
    /**
     * main method.
     * @param args ..
     */
    public static void main(String[] args) {
        MenuUnitSimple oneOneOne = new MenuUnitSimple("1.1.1.", "Task");
        MenuUnitSimple oneOneTwo = new MenuUnitSimple("1.1.2.", "Task");
        MenuUnitSimple oneTwoOne = new MenuUnitSimple("1.2.1.", "Task");
        MenuUnitSimple oneTwoTwo = new MenuUnitSimple("1.2.2.", "Task");

        ArrayList<MenuUnitSimple> oneOneChildren = new ArrayList<>();
        oneOneChildren.add(oneOneOne);
        oneOneChildren.add(oneOneTwo);

        ArrayList<MenuUnitSimple> oneTwoChildren = new ArrayList<>();
        oneTwoChildren.add(oneTwoOne);
        oneTwoChildren.add(oneTwoTwo);

        MenuUnitSimple oneOne = new MenuUnitSimple(oneOneChildren, "1.1.", "Task");
        MenuUnitSimple oneTwo = new MenuUnitSimple(oneTwoChildren, "1.2.", "Task");

        ArrayList<MenuUnitSimple> oneChildren = new ArrayList<>();
        oneChildren.add(oneOne);
        oneChildren.add(oneTwo);

        MenuUnitSimple one = new MenuUnitSimple(oneChildren, "1.", "Task");

        MenuUnitSimple twoOne = new MenuUnitSimple("2.1.", "Task");

        ArrayList<MenuUnitSimple> twoChildren = new ArrayList<>();
        twoChildren.add(twoOne);

        MenuUnitSimple two = new MenuUnitSimple(twoChildren, "2.", "Task");

        ArrayList<MenuUnitSimple> rootChildren = new ArrayList<>();
        rootChildren.add(one);
        rootChildren.add(two);

        MenuUnitSimple root = new MenuUnitSimple(rootChildren, "0", "Root");

        root.show();
        root.executeAction("2.1.");
    }

}
