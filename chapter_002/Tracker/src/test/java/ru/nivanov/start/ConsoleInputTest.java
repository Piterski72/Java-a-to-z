package ru.nivanov.start;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ConsoleInputTest class.
 * @author nivanov.
 * @since
 * @version
 */

public class ConsoleInputTest {
	/**
	* Test for console input.
	*
	*/
	@Test
	public void whenInput123ThenReturnOk() {
		System.setIn(new ByteArrayInputStream("123".getBytes()));
			ConsoleInput test = new ConsoleInput();
			assertThat(test.ask("enter 123\n"), is("123"));
	}
	/**
	* Test for exception.
	*
	*/
	@Test
	public void whenNotExceptionThenReturnInput() {
		System.setIn(new ByteArrayInputStream("2".getBytes()));
			ConsoleInput test = new ConsoleInput();
			final int[] row = {1, 2, 3};
			int expect = test.ask("Enter number: ", row);
			assertThat(expect, is(2));
	}
	/**
	* Test for exception2.
	*
	*/
	@Test
	public void whenExceptionTrueThenReturnMOE() {
		System.setIn(new ByteArrayInputStream("4".getBytes()));
			ConsoleInput test = new ConsoleInput();
			final int[] row = {1, 2, 3};
			boolean err = false;
			try {
				test.ask("Enter number: ", row);
				} catch (MenuOutException moe) {
					err = true;
				}
			assertThat(err, is(true));
	}
}