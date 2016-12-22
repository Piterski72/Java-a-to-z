package ru.nivanov;
import java.io.*;

/**
 * Programm checks if even number is in input flow.
 * @author nivanov.
 * @since
 * @version
 */
 public class Palindrom {
	private final int five = 5;
	/**
	* method checks if palindrom is in input flow.
	* @param phrase ..
	* @return boolean result
	*/
	boolean isPalindrom(String phrase) {
		boolean match = false;
		if (phrase.length() == five) {
				char[] reverse = new char[phrase.length()];
					for (int i = 0; i < phrase.length(); i++) {
						reverse[i] = phrase.charAt(phrase.length() - 1 - i);
					}
				String strReverse = new String(reverse);
				if (phrase.equalsIgnoreCase(strReverse)) {
					match = true;
				}
			} else {
				System.out.println("error, 5 symbols needed");
			}
		return match;
	}
}