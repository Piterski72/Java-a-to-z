package ru.nivanov;

/**
 * Programm checks if string "sub" is substring of "origin".
 * @author nivanov.
 * @since
 * @version
 */

class SubOrigin {
		//checks if string "sub" is substring of "origin".
	/**
	* Calc distance.
	* @param origin main string
	* @param sub substring
	* @return count true if sub is substring
	*/
	boolean contains(String origin, String sub) {
		char[]chOrig = origin.toCharArray();
		char[]chSub = sub.toCharArray();
		int index = 0;
		int count = 0;
		boolean result = false;
		for (int j = 0; j < chSub.length; j++) {
			for (int i = index; i < chOrig.length; i++) {
				if (chSub[j] == (chOrig[i])) {
					index = i + 1;
					count++;
					break;
				} else {
				count = 0;
				}
			}
		}
		return count == chSub.length;
	}
}