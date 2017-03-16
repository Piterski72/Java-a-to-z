package ru.nivanov;

/**
 * Choosing triangle maximum side.
 * @author nivanov.
 */

class MaxSide {
    /**
     * Choosing triangle maximum side.
     * @param a point a.
     * @param b point b.
     * @param c point c.
     * @return result
     */
    double maxChoose(Point a, Point b, Point c) {
        // choosing Max Side
        double maximum = Math.max(Math.max(a.distanceTo(b), b.distanceTo(c)), a.distanceTo(c));
        return maximum;
    }
}

