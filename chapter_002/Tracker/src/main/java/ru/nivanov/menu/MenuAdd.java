package ru.nivanov.menu;
import ru.nivanov.start.*;
import ru.nivanov.models.*;
import java.util.*;

/**
 * MenuAdd class.
 * @author nivanov.
 * @since
 * @version
 */
 public class MenuAdd implements MenuItem {
	/**
	 * Executing menu action.
	 * @param input ..
	 * @param tracker ..
	 * @return ..
	 */
	public String doStuff(Input input, Tracker tracker) {
		String name = input.ask("Enter task`s name: ");
		String desc = input.ask("Enter task`s desc: ");
		tracker.add(new Task(name, desc));
		System.out.println("Item created!");
		return "ok";
	}
}