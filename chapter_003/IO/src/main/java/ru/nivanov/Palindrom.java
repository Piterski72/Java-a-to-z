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
	* @param in ..
	* @return boolean result
	* @throws IOException ..
	*/
	boolean isPalik(InputStream in) throws IOException {
		boolean match = false;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			String value = br.readLine();
			if (value.length() == five) {
				char[] reverse = new char[value.length()];
					for (int i = 0; i < value.length(); i++) {
						reverse[i] = value.charAt(value.length() - 1 - i);
					}
				String strReverse = new String(reverse);
				if (value.equalsIgnoreCase(strReverse)) {
					match = true;
				}
			} else {
				System.out.println("error, 5 symbols needed");
			}
		}
		return match;
	}
}