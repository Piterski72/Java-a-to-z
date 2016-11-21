 package ru.nivanov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Calculate quadreant formula test.
 * @author nivanov.
 * @since
 * @version
 */

public class SquareTest {
	/**
	* Calculate quadreant (ax2+bx+c).
	*/
	@Test
		public void whenCalculateYThenReturnOk() {
			final float expected = 15;
			final int three = 3;
			Square squareOne = new Square(2, three, 1);
			float resultOne = squareOne.calculate(2);
		assertThat(resultOne, is(expected));
		}
	/**
	* Return quadreant (ax2+bx+c)values with defined step.
	*/
	@Test
		public void whenReturn5valuesYThenReturnLastValue() {
			final float expected = 38;
			final int one = 1;
			final int two = 2;
			final int three = 3;
			final int four = 5;
			int count = 0;
			Square squareOne = new Square(one, two, three);
			while (count < four) {
				squareOne.calculate(count);
				count++;
			}
			assertThat(squareOne.calculate(count), is(expected));
		}
}