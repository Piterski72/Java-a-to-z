package ru.nivanov.start;
/**
 * Input interface.
 * @author nivanov.
 * @since
 * @version
 */
public interface Input {
	/**
	 * Wait for user input.
	 * @param question ..
	 *
	 * @return ..
	 */
	String ask(String question);
	/**
	 * Overloads user input.
	 * @param question ..
	 * @param range ..
	 * @return ..
	 */
	int ask(String question, int[] range);
}