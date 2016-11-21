package ru.nivanov.start;
import ru.nivanov.models.*;
import java.util.*;

/**
 * Tracker.
 * @author nivanov.
 * @since
 * @version
 */

public class Tracker {
	/**
	*/
	private final int quant = 15;
	/**
	*/
	private Item[] items = new Item[quant];
	/**
	*/
	private int position = 0;
	/**
	*/
	private static final Random RN = new Random();
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
	 * @param comment input parameter
	 * @return item with comment
	 */
	public Item addComment(Comments comment) {
		Item found = findById(comment.getIdComm());
		found.addCommentz(comment);
		return found;
	}
	/**
	 * Edit Items fields.
	 * @param pos input parameter index
	 * @param newName input parameter name
	 * @param newDesc input parameter description
	 *
	 */
	public void ieditOne(int pos, String newName, String newDesc) {
		items[pos].setName(newName);
		items[pos].setDescription(newDesc);
	}
	/**
	 * Edit2 Items fields.
	 * @param itemEdit input new Item with new fields
	 *
	 */
	public void ieditTwo(Item itemEdit) {
		for (int i = 0; i < this.items.length; i++) {
			if (this.items[i] != null && this.items[i].getId().equals(itemEdit.getId())) {
				this.items[i] = itemEdit;
				break;
			}
		}
	}
	/**
	 * Remove Item from list.
	 * @param pos is index
	 *
	 */
	public void remove(int pos) {
		items[pos] = null;
		position--;
		for (int i = pos; i < position; i++) {
			items[i] = items [i + 1];
		}
	}
	/**
	 * Find Item by Id.
	 * @param id is input parameter
	 * @return Item with id
	 */
	protected Item findById(String id) {
		Item result = null;
		for (Item item : items) {
			if (item != null && item.getId().equals(id)) {
				result = item;
				break;
			}
		}
		return result;
	}
	/**
	 * Find Item by Name.
	 * @param name is input parameter
	 * @return Item with name
	 */
	protected Item findByName(String name) {
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
	protected Item findByDesc(String desc) {
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
	String generateId() {
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