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
 public class MenuList implements MenuItem {
	/**
	 * Executing menu action.
	 * @param input ..
	 * @param tracker ..
	 * @return ..
	 */
	public String doStuff(Input input, Tracker tracker) {
		Item[] res = tracker.getAll();
		for (int j = 0; j < res.length; j++) {
			System.out.printf("%s %s %s \n", res[j].getId(), res[j].getName(), res[j].getDescription());
			for (int i = 0; i < res[j].getCountCom(); i++) {
				System.out.println(res[j].getComments()[i]);
			}
		}
		return "ok";
	}
 }