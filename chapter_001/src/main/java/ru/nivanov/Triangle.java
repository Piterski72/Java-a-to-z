package ru.nivanov;

import static java.lang.Math.*;

public class Triangle {

public Point a;
public Point b;
public Point c;

public Triangle(Point a, Point b, Point c) {
this.a = a;
this.b = b;
this.c = c;
}
public double area() {
	
	//calculate poluper
	
	double poluper=(this.a.distanceTo(b) + this.b.distanceTo(c) + this.a.distanceTo(c))/2;
	double triArea;
	
	MaxSide longest=new MaxSide();
	
	double xxx=longest.maxChoose (this.a, this.b, this.c);
	
	//Check triangle existing and calculate the triangle area

	if (poluper>xxx) {
	
		triArea=sqrt(poluper*(poluper-this.a.distanceTo(b))*(poluper-this.b.distanceTo(c))*(poluper-this.a.distanceTo(c)));
	
		}
	else {
		 triArea=0.0;
		System.out.println(String.format ("Impossible to create triangle, area =  %.2f", triArea));
		
		}

return triArea;
  }
}