package org.java8action.rtd;

import java.util.Comparator;

public class Point {

    private final int x;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private final int y;

    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point generatePoint(int x, int y) {
        return new Point(x, y);
    }

    public Point moveRightby(int x) {
        return new Point(this.x + x, this.y);
    }

    public static final Comparator<Point> compareByxAndthenY = java.util.Comparator.
            comparing(Point::getX)
            .thenComparing(Point::getY);
}
