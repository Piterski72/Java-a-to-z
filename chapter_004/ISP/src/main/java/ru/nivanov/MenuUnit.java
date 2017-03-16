package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 09.03.2017.
 */
public class MenuUnit implements MenuInfo, MenuAction {
    private String name;
    private String id;
    private MenuUnit prevUnit = null;
    private MenuUnit nextUnit = null;
    private MenuUnit subUnit = null;
    private MenuUnit superUnit = null;

    /**
     * Constructor for root element.
     * @param name ..
     * @param id ..
     */
    MenuUnit(String id, String name) {
        this.name = name;
        this.id = id;
    }

    /**
     * Constructor for first element.
     * @param superUnit ..
     * @param id ..
     * @param name ..
     */
    MenuUnit(MenuUnit superUnit, MenuUnit prev, String id, String name) {
        this.id = id;
        this.name = name;
        this.superUnit = superUnit;
        this.superUnit.subUnit = this;
        this.prevUnit = prev;

    }

    /**
     * Constructor for current element.
     * @param prev ..
     * @param id ..
     * @param name ..
     */
    MenuUnit(MenuUnit prev, String id, String name) {
        this.id = id;
        this.name = name;
        this.prevUnit = prev;
        this.prevUnit.nextUnit = this;
    }

    /**
     * Name getter.
     * @return ..
     */
    String getName() {
        return name;
    }

    /**
     * Previous member getter.
     * @return ..
     */
    MenuUnit getPrevUnit() {
        return prevUnit;
    }

    /**
     * Next member getter.
     * @return ..
     */
    MenuUnit getNextUnit() {
        return nextUnit;
    }

    /**
     * Sub member getter.
     * @return ..
     */
    MenuUnit getSubUnit() {
        return subUnit;
    }

    /**
     * Super member getter.
     * @return ..
     */
    MenuUnit getSuperUnit() {
        return superUnit;
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

}
