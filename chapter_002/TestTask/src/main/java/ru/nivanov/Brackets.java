package ru.nivanov;

/**
 * Programm checks a correct brackets position.
 * @author nivanov.
 * @since
 * @version
 */
 public class Brackets {
	private String str;
	private int[] leftBr;
	private int[] rightBr;
		public Brackets(String str) {
		this.str = str;
		}
	/**
	* method checks correct brackets position.
	* @return boolean result
	*/
		public boolean skobkiOk() {
			boolean isCorrect = false;
			char[] testChar = this.str.toCharArray();
			int found = 0;
				for (int i = 0; i < testChar.length; i++) {
					if (testChar[i] == '(' || testChar[i] == ')') {
						found++;
					}
				}
				if (found != 0 && found % 2 == 0) {
					leftBr = new int[found / 2];
					rightBr = new int[found / 2];
					int count1 = 0;
					int count2 = 0;
					for (int i = 0; i < testChar.length; i++) {
						if (testChar[i] ==  '(') {
							leftBr[count1] = i;
							count1++;
						} else if (testChar[i] ==  ')') {
							rightBr[count2] = i;
							count2++;
						}
					}
					for (int k = 0; k < found / 2; k++) {
						if (leftBr[k] < rightBr[k]) {
							isCorrect = true;
						} else {
							isCorrect = false;
							break;
						}
					}
				} else {
					isCorrect = false;
				}
		return isCorrect;
		}
}