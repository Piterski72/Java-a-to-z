package ru.nivanov;

import static java.lang.Math.*;

public class PersonalTask {
	int[] first;
	int[] second;
	
	public PersonalTask (int[] first, int[] second) {
		this.first = first;
		this.second = second;
	}
	
	public int[] sortedMass () {
		
		int[] result = new int [this.first.length + this.second.length];
			
		int indexFirst = 0;
		int indexSecond = 0;
		int count = 0;
		int naibol = Math.max(first[this.first.length-1],second[this.second.length-1]);
					
		int[] tempMass = new int[naibol + 1];
			
		for (int i = 0; i < (this.first.length);i++) {
			for (int j = 0; j < (this.second.length);j++) {
				indexFirst = this.first[i];
				tempMass[indexFirst] = indexFirst; 
				indexSecond = this.second[j];
				tempMass[indexSecond] = indexSecond; 
			}
		}
				
		for (int k = 0; k < (naibol+1); k++ ) {
			if (tempMass[k] != 0) {
				result[count] = tempMass[k];
				count++;
			}
			
		}
	
	return result;	
	}
		
}