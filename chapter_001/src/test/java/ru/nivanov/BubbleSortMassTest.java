package ru.nivanov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class BubbleSortMassTest {
	
	@Test
	public void whenSortCorrectThenReturnOk() {
	int[] values={7,12,3,8,2};
	BubbleSortMass testMas = new BubbleSortMass (values);
	testMas.bubbleSort(values);
	int [] expect={2,3,7,8,12};
	assertThat(values, is (expect)); 	 	
	}
}