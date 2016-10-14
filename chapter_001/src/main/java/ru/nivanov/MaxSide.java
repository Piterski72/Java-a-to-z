package ru.nivanov;

import static java.lang.Math.*;

public class MaxSide {
	
	public Point a;
	public Point b;
	public Point c;

  public MaxSide(Point a, Point b, Point c) {
	this.a = a;
	this.b = b;
	this.c = c;
	}
public double MaxChoose () {
	
	//calculate triangle Sides length
	
	double abSide=this.a.distanceTo(b);
	double bcSide=this.b.distanceTo(c);
	double acSide=this.a.distanceTo(c);
	
		
	// choosing Max Side
	
	double maximum = Math.max(Math.max(abSide,bcSide),acSide);
	
	
	return maximum;
	}
	
}

