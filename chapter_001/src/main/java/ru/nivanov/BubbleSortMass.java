package ru.nivanov;

public class BubbleSortMass {
	int [] values;
	
	public BubbleSortMass(int[] values) {
		this.values = values;
	} 
	
	
	public void bubbleSort(int[] values){
    
		
		for(int i = values.length-1 ; i > 0 ; i--){
			for(int j = 0 ; j < i ; j++){
           
				
				if( values[j] > values[j+1] ){
                int change = values[j];
                values[j] = values[j+1];
                values[j+1] = change;
				}
			}
		}
	}
	
	
		
}