package ru.nivanov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Calculate triangle square.
 * @author nivanov.
 * @since
 * @version
 */

	public class TriangleTest {
	/**
	* Calculate square.
	*/
		@Test
	public void whenABCCorrectThenReturnArea() {
		final double one = 2.0;
		final double two = 1.0;
		final double three = -1.0;
		final double four = -3.0;
		final double five = -1.0;
		final double six = 1.0;
		final double seven = 6.0;
		Triangle abcOne = new Triangle(new Point(one, two), new Point(three, four), new Point(five, six));
		double ploShad = abcOne.area();
		assertThat(ploShad, is(seven));
    }
	/**
	* Test if triangle cannot exist.
	*/
		@Test
	public void whenABCinLineThenReturnError() {
		final double one = 1.0;
		final double two = 1.0;
		final double three = 5.0;
		final double four = 5.0;
		final double five = 10.0;
		final double six = 10.0;
		final double seven = 0.0;
		Triangle abcTwo = new Triangle(new Point(one, two), new Point(three, four), new Point(five, six));
		double ploShad = abcTwo.area();
		assertThat(ploShad, is(seven));
	}
}