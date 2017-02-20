package ru.nivanov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Programm checks if even number is in input flow.
 * @author nivanov.
 * @since
 * @version
 */
class ByteStream {
	/**
	* method checks if even number is in input flow.
	* @param in ..
	* @return boolean result
	* @throws IOException ..
	*/
	boolean isNumber(InputStream in) throws IOException {
		boolean evenOk = false;
		int i = 0;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			String value = br.readLine();
			System.out.println(value);
			String[] result = value.split(" ");
				while (i < result.length) {
					try {
						if (Integer.valueOf(result[i]) % 2 == 0) {
							evenOk = true;
						}
							i++;
					} catch (NumberFormatException nfe) {
						i++;
					}
				}
		} catch (IOException ioe) {
				ioe.printStackTrace();
			}
	return evenOk;
	}
 }