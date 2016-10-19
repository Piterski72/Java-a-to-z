 package ru.nivanov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SquareTest {
	
	@Test
	public void whenCalculateYThenReturnOk() {
	Square squareOne = new Square(2,3,1);
	float resultOne = squareOne.calculate (2);
    assertThat(resultOne, is (15f)); 	 	
	}
	
}