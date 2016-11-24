package ru.nivanov.start;

/**
 * StartUITest class.
 * @author nivanov.
 * @since
 * @version
 */

public class StartUITest {
	/**
	* main method.
	* @param args input param
	*/
	public static void main(String[] args) {
		Input input = new StubInput(new String[] {"create stub task"});
		new StartUI(input).init();
	}
}