package ru.nivanov.menu;
import ru.nivanov.start.*;
/**
 * MenuItem interface.
 * @author nivanov.
 * @since
 * @version
 */
public interface MenuItem {
	/**
	 * Executing menu action.
	 * @param input ..
	 * @param tracker ..
	 * @return ..
	 */
	String doStuff(Input input, Tracker tracker);
}