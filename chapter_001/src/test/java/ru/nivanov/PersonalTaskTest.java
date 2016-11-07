package ru.nivanov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PersonalTaskTest {
	
	@Test
	public void whenSortingCorrectThenReturnOk() {
	int[] one = {1,3,5,11,12,16};
	int[] two = {2,4,7,8};
	int [] expect = {1,2,3,4,5,7,8,11,12,16};
	PersonalTask res1 = new PersonalTask (one, two);
	assertThat(res1.sortedMass(), is (expect)); 
	}
}