package ru.nivanov.menu;
import ru.nivanov.start.*;
import ru.nivanov.models.*;
import java.util.*;

/**
 * MenuRem class.
 * @author nivanov.
 * @since
 * @version
 */
 public class MenuRem implements MenuItem {
	/**
	 * Executing menu action.
	 * @param input ..
	 * @param tracker ..
	 * @return ..
	 */
	public String doStuff(Input input, Tracker tracker) {
		String itemId = input.ask("Enter Id: ");
		int before = tracker.getPosition();
		tracker.remove(itemId);
		if (tracker.getPosition() < before) {
			System.out.println("Item deleted!");
		} else {
			System.out.println("ID is not correct!");
		}
		return "ok";
	}
}