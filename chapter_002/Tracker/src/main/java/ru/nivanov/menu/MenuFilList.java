package ru.nivanov.menu;
import ru.nivanov.start.*;
import ru.nivanov.models.*;
import java.util.*;

/**
 * MenuList class.
 * @author nivanov.
 * @since
 * @version
 */
 public class MenuFilList implements MenuItem {
	/**
	 * Executing menu action.
	 * @param input ..
	 * @param tracker ..
	 * @return ..
	 */
	public String doStuff(Input input, Tracker tracker) {
		String cho = input.ask("Choose filtering: name - by name, desc - by description: ");
		if (cho.equals("name")) {
			String name = input.ask("Enter name: ");
			for (Item item : tracker.getByName(name)) {
			System.out.printf("%s %s %s \n", item.getId(), item.getName(), item.getDescription());
			}
		} else if (cho.equals("desc")) {
			String desc = input.ask("Enter description: ");
			for (Item item : tracker.getByDesc(desc)) {
			System.out.printf("%s %s %s \n", item.getId(), item.getName(), item.getDescription());
			}
		} else {
			System.out.println("incorrect choice");
			}
		return "ok";
	}
}