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
	
	//calculate triangle Sides length and poluper
	
	double abSide=this.a.distanceTo(b);
	double bcSide=this.b.distanceTo(c);
	double acSide=this.a.distanceTo(c);
	double poluper=(abSide + bcSide + acSide)/2;
	double tri_area;
	
	//Check triangle existing and calculate the triangle area

	if (poluper>abSide && poluper>bcSide && poluper>acSide) {
	
		tri_area=sqrt(poluper*(poluper-abSide)*(poluper-bcSide)*(poluper-acSide));
	
		}
	else {
		 tri_area=0.0;
		System.out.println("Impossible to create triangle, area= " + tri_area);
		
		}

return tri_area;
  }
}