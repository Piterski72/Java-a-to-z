package ru.nivanov;

import static java.lang.Math.*;

public class Square {
	
	int a;
	int b;
	int c;
	int x1;
	int x2;
	
	public Square (int a, int b, int c) {
	this.a = a;
	this.b = b;
	this.c = c;
	}
	
		float calculate (int x) {
		
			float y = (float) (this.a * Math.pow((x),2) + this.b * x + this.c);	
			return y;
		}
		
			
		void show (int start, int finish, int step) {
			Square square = new Square (a, b, c);	
			while ( start < finish ) {
			float result = square.calculate(start);
			System.out.println (String.format("x = %.1f, y = %.1f", start, result ));
			start = start + step;
			}
			
		}
}	