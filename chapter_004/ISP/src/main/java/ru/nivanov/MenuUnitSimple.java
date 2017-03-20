package ru.nivanov;

import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 20.03.2017.
 */
public class MenuUnitSimple implements MenuInfo, MenuAction {
    private final String name;
    private final String id;
    private final boolean root;
    private ArrayList<MenuUnitSimple> children;

    /**
     * Constructor for local root element.
     * @param name ..
     * @param id ..
     */
    MenuUnitSimple(ArrayList<MenuUnitSimple> children, String id, String name) {
        this.name = name;
        this.id = id;
        this.children = children;
        this.root = true;
    }

    /**
     * Constructor for child element.
     * @param name ..
     * @param id ..
     */
    MenuUnitSimple(String id, String name) {
        this.name = name;
        this.id = id;
        this.root = false;
    }

    /**
     * Name getter.
     * @return ..
     */
    private String getName() {
        return name;
    }

    @Override
    public void showInfo() {
        System.out.println(String.format("%s%s %s", decorateInfo(key()), getName(), key()));
    }

    @Override
    public String key() {
        return this.id;
    }

    @Override
    public void execute() {
        System.out.println(String.format("menu item: %s executing action ...", key()));
    }

    /**
     * Method for decorating info with "-"  symbols.
     * @param input ..
     * @return ..
     */
    private String decorateInfo(String input) {
        StringBuilder decorate = new StringBuilder("");
        for (int i = 0; i < (input.length() - 2); i++) {
            decorate.append("-");
        }
        return decorate.toString();
    }

    /**
     * Getter for children.
     * @return ..
     */
    private ArrayList<MenuUnitSimple> getChildren() {
        return this.children;
    }

    /**
     * Getter for root.
     * @return ..
     */
    private boolean isRoot() {
        return root;
    }

    /**
     * Recursive show.
     */
    public void show() {
        showInfo();
        if (isRoot()) {
            for (int i = 0; i < getChildren().size(); i++) {
                getChildren().get(i).show();
            }
        }
    }

    /**
     * Recursive select.
     * @param input ..
     */
    public void executeAction(String input) {
        if (input.equals(key())) {
            execute();
        } else if (isRoot()) {
            for (MenuUnitSimple item : getChildren()) {
                item.executeAction(input);
            }
        }
    }
}
