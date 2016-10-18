package ru.nivanov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TriangleTest {
	
	@Test
	public void whenABCCorrectThenReturnArea() {
	  Triangle abcOne=new Triangle(new Point(2.0,1.0), new Point (-1.0,-3.0), new Point(-1.0,1.0));
      double ploshad=abcOne.area ();
      assertThat(ploshad, is (6.0d));
    }
	
    @Test
	public void whenABCinLineThenReturnError() {
	  Triangle abcTwo=new Triangle(new Point(1.0,1.0), new Point (5.0,5.0), new Point(10.0,10.0));
	  double ploshad=abcTwo.area ();
      assertThat(ploshad, is (0.0d));	  
	}
	
	
}	