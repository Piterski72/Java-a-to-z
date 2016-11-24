package ru.nivanov.menu;
import ru.nivanov.start.*;
import ru.nivanov.models.*;
import java.util.*;

/**
 * MenuEdit class.
 * @author nivanov.
 * @since
 * @version
 */
 public class MenuEdit implements MenuItem {
	/**
	 * Executing menu action.
	 * @param input ..
	 * @param tracker ..
	 * @return ..
	 */
	public String doStuff(Input input, Tracker tracker) {
		String itemId = input.ask("Enter Id: ");
		String name = input.ask("Enter new name: ");
		String desc = input.ask("Enter new description: ");
		tracker.ieditTwo(itemId, name, desc);
		return "ok";
	}
}