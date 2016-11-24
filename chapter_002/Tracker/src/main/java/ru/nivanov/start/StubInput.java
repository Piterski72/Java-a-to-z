package ru.nivanov.start;
/**
 * StubInput class.
 * @author nivanov.
 * @since
 * @version
 */
public class StubInput implements Input {
	private String[] answers;
	private int position = 0;
	public StubInput(String[] answers) {
		this.answers = answers;
	}
	/**
	 * Ask realization.
	 * @param question ..
	 * @return answers ..
	 */
	public String ask(String question) {
		return answers[position++];
	}
}