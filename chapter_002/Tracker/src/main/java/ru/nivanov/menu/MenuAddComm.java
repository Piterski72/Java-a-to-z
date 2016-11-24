package ru.nivanov.menu;
import ru.nivanov.start.*;
import ru.nivanov.models.*;
import java.util.*;

/**
 * MenuAddComm class.
 * @author nivanov.
 * @since
 * @version
 */
 public class MenuAddComm implements MenuItem {
	/**
	 * Executing menu action.
	 * @param input ..
	 * @param tracker ..
	 * @return ..
	 */
	public String doStuff(Input input, Tracker tracker) {
		String itemId = input.ask("Enter Id: ");
		String comms = input.ask("Enter comment: ");
		tracker.addComment(itemId, comms);
		return "ok";
	}
}