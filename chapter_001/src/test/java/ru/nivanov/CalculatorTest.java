package ru.nivanov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test Calculator methods.
 * @author nivanov.
 * @since
 * @version
 */

public class CalculatorTest {
	/**
	* Test of summ.
	*/
	@Test
		public void whenSummCorrectThenReturnSumm() {
		final double one = 4.55;
		final double two = 2.45;
		final double three = 7.00;
		Calculator calc = new Calculator();
		calc.add(one, two);
		double result = calc.getResult();
		assertThat(result, is(three));
    }
	/**
	* Test of substract.
	*/
	@Test
		public void whenSubtractCorrectThenReturnSubtract() {
		final double one = 4.50;
		final double two = 2.50;
		final double three = 2.00;
		Calculator calc = new Calculator();
		calc.subtract(one, two);
		double result = calc.getResult();
		assertThat(result, is(three));
	}
	/**
	* Test of multiply.
	*/
	@Test
		public void whenMultipleCorrectThenReturnMultiple() {
		final double one = 4.00;
		final double two = 2.00;
		final double three = 8.00;
		Calculator calc = new Calculator();
		calc.multiple(one, two);
		double result = calc.getResult();
		assertThat(result, is(three));
	}
	/**
	* Test of division.
	*/
	@Test
		public void whenDivCorrectThenReturnDiv() {
		final double one = 4.00;
		final double two = 2.00;
		final double three = 2.00;
		Calculator calc = new Calculator();
		calc.div(one, two);
		double result = calc.getResult();
		assertThat(result, is(three));
	}
	/**
	* Test of division.
	*/
	@Test
		public void whenDivByZeroThenReturnZero() {
		final double one = 4.00;
		final double two = 0.00;
		final double three = 0.00;
		Calculator calc = new Calculator();
		calc.div(one, two);
		double result = calc.getResult();
		assertThat(result, is(three));
	}
	/**
	* Test of get result1.
	*/
	@Test
		public void whenGetResultWorksThenReturnItValue() {
		Calculator calc = new Calculator();
		final double expected = 0.0;
		assertThat(calc.getResult(), is(expected));
    }
}