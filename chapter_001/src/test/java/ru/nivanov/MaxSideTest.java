package ru.nivanov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MaxSideTest {
	
	@Test
	public void whenCalculateMaxThenReturnMaxOk() {
	  
	  MaxSide abc = new MaxSide (new Point (2.0,1.0),new Point (-1.0,-3.0),new Point (-1.0,1.0));
	  double result = abc.MaxChoose ();
	  assertThat(result, is (5.0d));	
	
	}


}