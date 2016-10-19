package ru.nivanov;

import static java.lang.Math.*;

public class Square {
	
	int a;
	int b;
	int c;
	
	public Square (int a, int b, int c) {
	this.a = a;
	this.b = b;
	this.c = c;

	}
	
	void calculating () {
		
		Square square = new Square (a, b, c);
		
		// Calculate diskriminant and check is solution exists
		
		float diskr = (float)(Math.pow((this.b),2) - 4 * this.a * this.c);
		
		if (diskr < 0.00) {
			System.out.println("No solution");
					}
							
		else {
			
			float x1 = (float) ((-this.b + sqrt(diskr))/(2 * this.a));
			float x2 = (float) ((-this.b - sqrt(diskr))/(2 * this.a));
			float step = (float) (-0.1);
			
			while ( x1 > x2 ) {
					float result = square.calculate (x1);
					square.show (x1, x2, step, result);
					x1 = x1 + step;
				}
				
				}
		}									
	void show (float start, float finish, float step, float y) {
		System.out.println(String.format ("x1: %.1fd, x2: %.1fe, step: %.1ff, Func y is: %.1fg", start, finish, step, y));
		}
	float calculate (float x) {
		float y = (float) (this.a * Math.pow((x),2) + this.b * x + this.c);
		return y;
		}
		
	
}	