package ru.nivanov.start;
import java.util.*;
/**
 * ConsoleInput class.
 * @author nivanov.
 * @since
 * @version
 */
public class ConsoleInput implements Input {
	private Scanner scanner = new Scanner(System.in);
	/**
	 * Wait for user input.
	 * @param question ..
	 *
	 * @return user choice
	 */
	public String ask(String question) {
		System.out.print(question);
		return scanner.nextLine();
	}

}