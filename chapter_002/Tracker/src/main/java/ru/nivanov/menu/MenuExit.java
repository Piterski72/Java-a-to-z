package ru.nivanov.menu;
import ru.nivanov.start.*;
import ru.nivanov.models.*;
import java.util.*;

/**
 * MenuExit class.
 * @author nivanov.
 * @since
 * @version
 */
 public class MenuExit implements MenuItem {
	/**
	 * Executing menu action.
	 * @param input ..
	 * @param tracker ..
	 * @return ..
	 */
	public String doStuff(Input input, Tracker tracker) {
		System.out.println("Exiting!");
		return "ok";
	}
}