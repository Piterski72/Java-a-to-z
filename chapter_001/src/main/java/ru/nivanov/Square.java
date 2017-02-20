package ru.nivanov;

/**
 * Calculates quadratic.
 * @author nivanov.
 * @since
 * @version
 */
class Square {
	/**
	*/
	private final int a;
	/**
	*/
	private final int b;
	/**
	*/
	private final int c;
	/**
	 * Class Constructor.
	 * @param d coeff.
	 * @param e coeff.
	 * @param f coeff.
	 */
	Square(int d, int e, int f) {
		this.a = d;
		this.b = e;
		this.c = f;
		}
	/**
	* quadratic formula.
	* @param x ..
	* @return result
	*/
		float calculate(int x) {
			return (float) (this.a * Math.pow((x), 2) + this.b * x + this.c);
			}
}