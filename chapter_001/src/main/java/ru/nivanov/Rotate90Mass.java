package ru.nivanov;

/**
 * Rotating square massive.
 * @author nivanov.
 * @since
 * @version
 */

class Rotate90Mass {
	/**
	*/
	private final int[][] values;
	/**
	 * Class Constructor.
	 * @param vals 2-dim massive
	 */
	public Rotate90Mass(int[][] vals) {
		this.values = vals;
	}
	/**
	* rotate.
	*/
	public void rotation90() {
		int max = this.values.length;
		int tmp = 0;
		for (int i = 0; i < max; i++) {
			for (int j = i; j < max - i - 1; j++) {
				tmp = this.values [i][j];
				this.values[i][j] = this.values[(max - j - 1)][i];
				this.values[(max - j - 1)][i] = this.values[(max - i - 1)][max - j - 1];
				this.values[(max - i - 1)][max - j - 1] = this.values[j][max - i - 1];
				this.values[j][max - i - 1] = tmp;
			}
		}
	}
}