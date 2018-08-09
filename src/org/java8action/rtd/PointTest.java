package org.java8action.rtd;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

    @Test
    public void test() {
        Point p1 = Point.generatePoint(10, 15);
        Point p2 = p1.moveRightby(5);
        assertEquals(15, p2.getX());
        assertEquals(15, p2.getY());

    }

    @Test
    public void compareest() {
        Point p1 = Point.generatePoint(11, 19);
        Point p2 = Point.generatePoint(12, 18);
        int result=Point.compareByxAndthenY.compare(p1, p2);
        System.out.println(result);

    }

}
