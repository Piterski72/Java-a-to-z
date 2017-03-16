package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 09.03.2017.
 */
class Menu {

    /**
     * Show all menu from root.
     * @param value ..
     */
    void show(MenuUnit value) {
        MenuUnit root = value;
        do {
            value = downTree(value);
            value = listMembersOfSameHierarchy(value);
            value = reset(value);
        } while (value != root);
    }

    /**
     * Down tree to lowest member.
     * @param item ..
     * @return ..
     */
    private MenuUnit downTree(MenuUnit item) {
        while (item.getSubUnit() != null) {
            item = item.getSubUnit();
            item.showInfo();
        }
        return item;
    }

    /**
     * Show members of same hierarchy.
     * @param item ..
     * @return ..
     */
    private MenuUnit listMembersOfSameHierarchy(MenuUnit item) {
        while (item.getNextUnit() != null) {
            item = item.getNextUnit();
            item.showInfo();
        }
        return item;
    }

    /**
     * Reset to first item.
     * @param item ..
     * @return ..
     */
    private MenuUnit reset(MenuUnit item) {
        while (item.getPrevUnit() != null) {
            item = item.getPrevUnit();
        }
        if (item.getSuperUnit() != null) {
            item = item.getSuperUnit();
        }
        if (item.getNextUnit() != null) {
            item = item.getNextUnit();
            item.showInfo();
        } else {
            while (item.getPrevUnit() != null) {
                item = item.getPrevUnit();
            }
            if (item.getSuperUnit() != null) {
                item = item.getSuperUnit();
                if (item.getNextUnit() != null) {
                    item = item.getNextUnit();
                    item.showInfo();
                }
            }
        }
        return item;
    }
}
