package ru.nivanov;
import static java.lang.Math.*;

/**
 * Calculate distance between two points.
 * @author nivanov.
 * @since
 * @version
 */

public class Point {
	/**
	*/
	private double x;
	/**
	*/
	private double y;
	/**
	 * Class Constructor.
	 * @param a first coordinate
	 * @param b second coordinate
	 */
	public Point(double a, double b) {
	this.x = a;
	this.y = b;
	}
	/**
	* Calc distance.
	* @param point with coordinates
	* @return distance
	*/
	public double distanceTo(Point point) {
		//calculate distance between two points
		return Math.sqrt(Math.pow((point.y - this.y), 2) + Math.pow((point.x - this.x), 2));
	}
}