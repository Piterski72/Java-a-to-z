package ru.nivanov.start;
import ru.nivanov.models.*;
import ru.nivanov.menu.*;

/**
 * StartUI class.
 * @author nivanov.
 * @since
 * @version
 */

public class StartUI {
	private Input input;
	public StartUI(Input input) {
		this.input = input;
	}
	/**
	 * realization.
	 * ..
	 * ..
	 */
	public void init() {
		final int getout = 7;
		Tracker tracker = new Tracker();
		MenuItem[] str = new MenuItem[] {new MenuAdd(), new MenuEdit(), new MenuRem(), new MenuList(), new MenuFilList(), new MenuAddComm(), new MenuExit()};
		int menuch;
		do {
			System.out.println("0 - add, 1 - edit, 2 - del, 3 - list, 4 - filter, 5 - add comment, 6 - exit");
			String usrch = input.ask("Please, enter your choice: ");
			menuch = Integer.valueOf(usrch);
			str[menuch].doStuff(input, tracker);
			} while (menuch + 1 < getout);
	}
	/**
	* main method.
	* @param args input param
	*/
	public static void main(String[] args) {
		Input input = new ConsoleInput();
		new StartUI(input).init();
	}
}