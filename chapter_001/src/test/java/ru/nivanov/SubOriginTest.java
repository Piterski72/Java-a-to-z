package ru.nivanov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SubOriginTest {
	
	@Test
	public void whenSubIsSubstringThenReturnTrue() {
	String str1 = "abcdef";
	String str2 = "cde";
	SubOrigin res0 = new SubOrigin();
	assertThat(res0.contains (str1,str2), is (true)); 
	}	
	
	@Test
	public void whenSubIsNotSubstringThenReturnFalse() {
	String str3 = "abcdef";
	String str4 = "cdf";
	SubOrigin res1 = new SubOrigin();
	assertThat(res1.contains (str3,str4), is (false)); 
	}	
	
	
	
}