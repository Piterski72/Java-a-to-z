package ru.nivanov.start;

import ru.nivanov.models.Item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Tracker.
 * @author nivanov.
 */

class Tracker {
    /**
     */
    private static final Random RN = new Random();
    /**
     */
    private final int quant = 15;
    /**
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Add Item.
     * @param item input parameter
     * @return item - add ok
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Add Items comment.
     * @param itemId input parameter
     * @param comment input parameter
     * @return item with comment
     */
    public Item addComment(String itemId, String comment) {
        Item result = findById(itemId);
        result.addCommentz(comment);
        return result;
    }

    /**
     * Edit2 Items fields.
     * @param itemId input Item Id
     * @param name input Item Name
     * @param desc input Item description
     */
    public void ieditTwo(String itemId, String name, String desc) {
        Item result = findById(itemId);
        result.setName(name);
        result.setDescription(desc);
    }

    /**
     * Remove Item from list.
     * @param itemId is item id
     */
    public void remove(String itemId) {
        Iterator<Item> iterator = this.items.iterator();
        Item current;
        while (iterator.hasNext()) {
            current = iterator.next();
            if (current.getId().equals(itemId)) {
                iterator.remove();
            }
        }
    }

    /**
     * Find Item by Id.
     * @param id is input parameter
     * @return Item with id
     */
    Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            } else {
                result = null;
            }
        }
        return result;
    }

    /**
     * Find Item by Name.
     * @param name is input parameter
     * @return Item with name
     */
    Item findByName(String name) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getName().equals(name)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Find Item by Description.
     * @param desc is input parameter
     * @return Item with description
     */
    Item findByDesc(String desc) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getDescription().equals(desc)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Find Item by Create info.
     * @param create is input parameter
     * @return Item with create info
     */
    protected Item findByCreate(long create) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getCreate() == create) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Generating id for new item.
     * @return new id for new Item
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Getting filtered list (all not null).
     * @return massive of items (not nulls)
     */
    public List<Item> getAll() {
        return this.items;
    }

    /**
     * Getting filtered list (by name).
     * @param name input parameter
     * @return massive of items
     */
    public List<Item> getByName(String name) {

        List<Item> tempN = new ArrayList<>();
        return tempN = this.items.stream().filter(p -> p.getName().equals(name)).collect(Collectors.toList());

    }

    /**
     * Getting filtered list (by description).
     * @param desc input parameter
     * @return massive of items
     */
    public ArrayList<Item> getByDesc(String desc) {
        ArrayList<Item> tempD = new ArrayList<>();
        for (Item item : this.items) {
            if (item.getDescription().equals(desc)) {
                tempD.add(item);
            }
        }
        return tempD;
    }
}