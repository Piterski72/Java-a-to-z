package ru.nivanov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class MaxSideTest {
	
	@Test
	public void whenCalculateMaxThenReturnMaxOk() {
	MaxSide longSide=new MaxSide();
    double testside = longSide.maxChoose (new Point(2.0,1.0), new Point (-1.0,-3.0), new Point(-1.0,1.0));
	assertThat(testside, is (5.0d)); 	 	
	}


}