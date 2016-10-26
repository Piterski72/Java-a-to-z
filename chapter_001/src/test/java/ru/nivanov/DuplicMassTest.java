package ru.nivanov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DuplicMassTest {
	@Test
	public void whenKillCopyCorrectThenReturnExpected() {
	String[] tex = {"a","a","x","d","e","c","f","h","d","m","c","x","b"};
	String[] itog ={"a","x","d","e","c","f","h","m","b"}; 
	DuplicMass hoho = new DuplicMass (tex);
	String [] nocopies = hoho.copyRemover();
	assertThat(nocopies, is (itog)); 	 	
		
	}
	
	
	
	
}