package ru.nivanov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class Rotate90MassTest {
	@Test
	public void whenRotateCorrectThenReturnExpected() {
		int[][] values = {{1,1,1,1},{2,2,2,2},{3,3,3,3},{4,4,4,4}};
		Rotate90Mass testrot = new Rotate90Mass(values);
		testrot.rotation90 ();
		int[][] expected = {{4,3,2,1},{4,3,2,1},{4,3,2,1},{4,3,2,1}};
		assertThat(values, is (expected)); 	 	
		
		
		
	}
	
	
	
	
	
}