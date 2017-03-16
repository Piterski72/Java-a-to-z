package ru.nivanov;

/**
 * Calculate distance between two points.
 * @author nivanov.
 */

class Point {
    /**
     */
    private final double x;
    /**
     */
    private final double y;

    /**
     * Class Constructor.
     * @param a first coordinate
     * @param b second coordinate
     */
    Point(double a, double b) {
        this.x = a;
        this.y = b;
    }

    /**
     * Calc distance.
     * @param point with coordinates
     * @return distance
     */
    double distanceTo(Point point) {
        //calculate distance between two points
        return Math.sqrt(Math.pow((point.y - this.y), 2) + Math.pow((point.x - this.x), 2));
    }
}