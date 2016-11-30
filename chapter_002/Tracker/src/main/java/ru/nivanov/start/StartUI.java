package ru.nivanov.start;
/**
 * StartUI class.
 * @author nivanov.
 * @since
 * @version
 */

public class StartUI {
	private Input input;
	private Tracker tracker;
	public StartUI(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}
	/**
	 * realization.
	 *
	 * ..
	 */
	public void init() {
		MenuTracker menu = new MenuTracker(this.input, this.tracker);
		menu.fillActions();
		do {
			menu.show();
			int key = Integer.valueOf(input.ask("Select action: "));
			menu.select(key);
			} while (!"y".equals(this.input.ask("Exit? (y) ")));
	}
	/**
	* main method.
	* @param args input param
	*/
	public static void main(String[] args) {
		Input input = new ConsoleInput();
		Tracker tracker = new Tracker();
		new StartUI(input, tracker).init();
	}
}