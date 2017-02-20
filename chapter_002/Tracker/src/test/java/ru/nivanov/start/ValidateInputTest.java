package ru.nivanov.start;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ValidateInputTest class.
 * @author nivanov.
 * @since
 * @version
 */

public class ValidateInputTest {
	/**
	* Test for validate correct input.
	*
	*/
	@Test
	public void whenInput2ThenReturnOk() {
		System.setIn(new ByteArrayInputStream("2".getBytes()));
		ValidateInput test = new ValidateInput();
		final int[] row = {1, 2, 3};
		int expect = test.ask("Enter number: ", row);
		assertThat(expect, is(2));
	}
	/**
	* Test for validate incorrect input one (out of range).
	*
	*/
	@Test
	public void whenInputZeroThenFalse() {
		ValidateInput test = new ValidateInput();
		System.setIn(new ByteArrayInputStream("0".getBytes()));
		final int[] row = {1, 2, 3};
		boolean err = false;
		try {
			test.ask("Enter number: ", row);
			} catch (Exception ex) {
				err = true;
			}
		assertThat(err, is(true));
	}
	/**
	* Test for validate incorrect input two (out of range).
	*
	*/
	@Test
	public void whenInput4ThenFalse() {
		ValidateInput test = new ValidateInput();
		System.setIn(new ByteArrayInputStream("4".getBytes()));
		final int[] row = {1, 2, 3};
		boolean err = false;
		try {
			test.ask("Enter number: ", row);
			} catch (Exception ex) {
				err = true;
			}
		assertThat(err, is(true));
	}
	/**
	* Test for validate incorrect input three (symbol).
	*
	*/
	@Test
	public void whenInputSymbolThenFalse() {
		ValidateInput test = new ValidateInput();
		System.setIn(new ByteArrayInputStream("q".getBytes()));
		final int[] row = {1, 2, 3};
		boolean err = false;
		try {
			test.ask("Enter number: ", row);
			} catch (Exception ex) {
				err = true;
			}
		assertThat(err, is(true));
	}
   /**
	* Test for validate create object.
	*
	*/
	@Test
	public void whenCreateObjThenReturnClassName() {
		ValidateInput test = new ValidateInput();
		assertThat(test.getClass().getName(), is("ru.nivanov.start.ValidateInput"));
	}
}