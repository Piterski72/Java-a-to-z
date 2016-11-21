package ru.nivanov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Personal task test.
 * @author nivanov.
 * @since
 * @version
 */

public class PersonalTaskTest {
	/**
	* Get result sorted massive of two sorted massives.
	*/
	@Test
	public void whenSortingCorrectThenReturnOk() {
	final int[] one = {1, 3, 5, 11, 12, 16};
	final int[] two = {2, 4, 7, 8};
	final int[] expect = {1, 2, 3, 4, 5, 7, 8, 11, 12, 16};
	PersonalTask res1 = new PersonalTask(one, two);
	assertThat(res1.sortedMass(), is(expect));
	}
}