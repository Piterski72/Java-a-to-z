package ru.nivanov;

import static java.lang.Math.sqrt;

/**
 * Calculate triangle area.
 * @author nivanov.
 */

class Triangle {
    /**
     */
    private final Point a;
    /**
     */
    private final Point b;
    /**
     */
    private final Point c;

    /**
     * Class Constructor.
     * @param x is point with coordinates
     * @param y is point with coordinates
     * @param z is point with coordinates
     */
    public Triangle(Point x, Point y, Point z) {
        this.a = x;
        this.b = y;
        this.c = z;
    }

    /**
     * Calc area.
     * @return triArea as calculated area
     */
    public double area() {
        //calculate poluper
        double poluper = (this.a.distanceTo(b) + this.b.distanceTo(c) + this.a.distanceTo(c)) / 2;
        double triArea;
        MaxSide longest = new MaxSide();
        double xxx = longest.maxChoose(this.a, this.b, this.c);
        //Check triangle existing and calculate the triangle area

        if (poluper > xxx) {
            triArea = sqrt(poluper * (poluper - this.a.distanceTo(b)) * (poluper - this.b.distanceTo(
                    c)) * (poluper - this.a.distanceTo(c)));
        } else {
            triArea = 0.0;
            System.out.println(String.format("Impossible to create triangle, area =  %.2f", triArea));
        }
        return triArea;
    }
}