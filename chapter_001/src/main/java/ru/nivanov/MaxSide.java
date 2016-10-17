package ru.nivanov;

import static java.lang.Math.*;



public class MaxSide {
	
	
	
	public double maxChoose (Point a, Point b, Point c) {
	
		
	// choosing Max Side
	
	
	double maximum = Math.max(Math.max(a.distanceTo(b),b.distanceTo(c)),a.distanceTo(c));
	
	
	return maximum;
	}
	
}

