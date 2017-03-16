package ru.nivanov.start;

import ru.nivanov.models.Item;

import java.util.Random;

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
    private final Item[] items = new Item[quant];
    /**
     */
    private int position = 0;

    /**
     * @return position
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Add Item.
     * @param item input parameter
     * @return item - add ok
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[position++] = item;
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
        int indItRm = 0;
        for (int i = 0; i < position; i++) {
            if (items[i].getId().equals(itemId)) {
                items[i] = null;
                indItRm = i;
                position--;
                for (int j = indItRm; j < position; j++) {
                    items[j] = items[j + 1];
                }
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
        for (Item item : items) {
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
    public Item[] getAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }

    /**
     * Getting filtered list (by name).
     * @param name input parameter
     * @return massive of items
     */
    public Item[] getByName(String name) {
        Item[] tempN = new Item[this.position];
        int count = 0;
        for (int index = 0; index != this.position; index++) {
            if (this.items[index].getName().equals(name)) {
                tempN[count] = this.items[index];
                count++;
            }
        }
        Item[] resultN = new Item[count];
        for (int i = 0; i < count; i++) {
            resultN[i] = tempN[i];
        }
        return resultN;
    }

    /**
     * Getting filtered list (by description).
     * @param desc input parameter
     * @return massive of items
     */
    public Item[] getByDesc(String desc) {
        Item[] tempD = new Item[this.position];
        int count = 0;
        for (int index = 0; index != this.position; index++) {
            if (this.items[index].getDescription().equals(desc)) {
                tempD[count] = this.items[index];
                count++;
            }
        }
        Item[] resultD = new Item[count];
        for (int i = 0; i < count; i++) {
            resultD[i] = tempD[i];
        }
        return resultD;
    }
}