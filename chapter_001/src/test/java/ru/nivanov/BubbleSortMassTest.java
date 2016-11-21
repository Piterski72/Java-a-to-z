package ru.nivanov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test of bubble sorting method.
 * @author nivanov.
 * @since
 * @version
 */

public class BubbleSortMassTest {
	/**
	* Test of bubble sorting method.
	*/
	@Test
	public void whenSortCorrectThenReturnOk() {
	final int[] values = {7, 12, 3, 8, 2};
	BubbleSortMass testMas = new BubbleSortMass(values);
	testMas.bubbleSort(values);
	final int[] expect = {2, 3, 7, 8, 12};
	assertThat(values, is(expect));
	}
}