package ru.nivanov;

/**
 * Calculating factorial.
 * @author nivanov.
 * @since
 * @version
 */

class Factorial {
	/**
	*/
	private final int a;
	/**
	* Constructor Factorial.
	* @param x ..
	*/
	public Factorial(int x) {
		this.a = x;
	}
	/**
	* Calculating factorial.
	*@return result
	*
	*/
	public int calcF() {
		int b = this.a;
		for (int i = 1; i < this.a; i++) {
		b = b * i;
		}
		System.out.println(String.format("factorial %d =  %d", a, b));
		return b;
	}
}