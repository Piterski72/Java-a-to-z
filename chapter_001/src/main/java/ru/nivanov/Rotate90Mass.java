package ru.nivanov;

public class Rotate90Mass {
	int [][] values;
	
	public Rotate90Mass (int [][] values) {
		this.values = values;
	}
	
	public void rotation90(int[][] values){
		
		int max = this.values.length;
		int tmp = 0;
		
		for (int i = 0; i < max; i++) {
				 
				for (int j = i; j < max-i-1; j++) {
									
					tmp = this.values [i][j];
					this.values [i][j] = this.values [(max -j-1)][i];
					this.values [(max -j-1)][i] = this.values [(max -i-1)][max-j-1];
					this.values [(max -i-1)][max-j-1] = this.values [j][max-i-1];
					this.values [j][max-i-1] = tmp;
												
				}
		}	
		
	
	
	
	}
}