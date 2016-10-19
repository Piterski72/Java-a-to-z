package ru.nivanov;

public class Factorial {
	
	int a;
	
	public Factorial (int a) {
		
	this.a = a;	
		
	}
	
	public int calcF () {
		
		int b = this.a;
		for (int i = 1; i < this.a; i++) {
		b = b * i;		
		}
		
		System.out.println (String.format("factorial %d =  %d", a, b));
		return b;
	}	

	
	
}