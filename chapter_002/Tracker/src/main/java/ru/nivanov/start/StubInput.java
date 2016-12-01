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
	/**
	 * ask method.
	 * @param question ..
	 * @param range ..
	 * @return value ..
	 */
	public int ask(String question, int[] range) {
		int key = Integer.valueOf(this.ask(question));
		boolean exist = false;
		for (int value : range) {
			if (value == key) {
				exist = true;
				break;
			}
		}
		if (exist) {
			return key;
		} else {
			throw new MenuOutException("Out of menu range");
		}
	}
}