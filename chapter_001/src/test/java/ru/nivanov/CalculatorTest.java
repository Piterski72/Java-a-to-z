package ru.nivanov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculatorTest {
	
	@Test
	public void whenSummCorrectThenReturnSumm() {
	  Calculator calc=new Calculator();
      calc.add(4.55,2.45);
	  double result= calc.getResult ();
      assertThat(result, is (7.00d));
    }
	
    @Test
	public void whenSubtractCorrectThenReturnSubtract() {
	  Calculator calc=new Calculator();
      calc.subtract(4.50,2.50); 
	  double result=calc.getResult ();
      assertThat(result, is (2.00d));	  
	}
	
	@Test
	public void whenMultipleCorrectThenReturnMultiple() {
	  Calculator calc=new Calculator();
      calc.multiple(4.00,2.00);
	  double result=calc.getResult ();
      assertThat(result, is (8.00d));	  
	}
	@Test
	public void whenDivCorrectThenReturnDiv() {
	  Calculator calc=new Calculator();
      calc.div(4.00,2.00);
	  double result=calc.getResult ();
      assertThat(result, is (2.00d));	  
	}
}	