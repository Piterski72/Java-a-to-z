package ru.nivanov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test if sub string is part of origin string.
 * @author nivanov.
 * @since
 * @version
 */

public class SubOriginTest {
	/**
	* Test for true.
	*/
	@Test
	public void whenSubIsSubstringThenReturnTrue() {
	String str1 = "abcdef";
	String str2 = "cde";
	SubOrigin res0 = new SubOrigin();
	assertThat(res0.contains(str1, str2), is(true));
	}
	/**
	* Test for false.
	*/
	@Test
	public void whenSubIsNotSubstringThenReturnFalse() {
	String str3 = "abcdef";
	String str4 = "cdf";
	SubOrigin res1 = new SubOrigin();
	assertThat(res1.contains(str3, str4), is(false));
	}
}