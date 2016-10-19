package ru.nivanov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class FactorialTest {
	
	@Test
	public void whenCalculateFactThenReturnIt() {
	Factorial factorial = new Factorial(6);
	int z = factorial.calcF();
    assertThat(z, is (720)); 	 	
	}
}