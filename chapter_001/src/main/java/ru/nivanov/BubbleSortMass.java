package ru.nivanov;

public class BubbleSortMass {
	int [] values;
	
	public BubbleSortMass(int[] values) {
		this.values = values;
	} 
	
	
	public void bubbleSort(int[] values){
    
		/* Внешний цикл за проход сокращает фрагмент массива, 
		внутренний перегоняет в конец макс. элемент.
		*/
		for(int i = values.length-1 ; i > 0 ; i--){
			for(int j = 0 ; j < i ; j++){
           
				/* Попарное сравнение элементов. 
				Если не по возрастанию, меняем элементы местами.
				*/
				if( values[j] > values[j+1] ){
                int change = values[j];
                values[j] = values[j+1];
                values[j+1] = change;
				}
			}
		}
	}
	
	
		
}