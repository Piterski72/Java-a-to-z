package ru.nivanov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class BubbleSortMassTest {
	
	@Test
	public void whenSortCorrectThenReturnOk() {
	int[] values=new int [5];
	values [0] = 7;
	values [1] = 12;
	values [2] = 3;
	values [3] = 8;
	values [4] = 2;
	BubbleSortMass testMas = new BubbleSortMass (values);
	testMas.bubbleSort(values);
	int [] expect=new int [5];
	expect [0] = 2;
	expect [1] = 3;
	expect [2] = 7;
	expect [3] = 8;
	expect [4] = 12;
	
	
    assertThat(values, is (expect)); 	 	
	}


}