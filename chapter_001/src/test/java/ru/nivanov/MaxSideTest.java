package ru.nivanov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test of finding max side of triangle.
 * @author nivanov.
 * @since
 * @version
 */

public class MaxSideTest {
	/**
	* Finding maximum side.
	*/
	@Test
	public void whenCalculateMaxThenReturnMaxOk() {
	final Point one = new Point(2.0, 1.0);
	final Point two = new Point(-1.0, -3.0);
	final Point three = new Point(-1.0, 1.0);
	final double expected = 5;
	MaxSide longSide = new MaxSide();
    double testside = longSide.maxChoose(one, two, three);
	assertThat(testside, is(expected));
	}
}