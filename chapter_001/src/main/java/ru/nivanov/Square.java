package ru.nivanov;
import static java.lang.Math.*;

/**
 * Calculates quadratic.
 * @author nivanov.
 * @since
 * @version
 */
	public class Square {
	/**
	*/
		private int a;
	/**
	*/
		private int b;
	/**
	*/
		private int c;
	/**
	 * Class Constructor.
	 * @param d coeff.
	 * @param e coeff.
	 * @param f coeff.
	 */
		public Square(int d, int e, int f) {
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