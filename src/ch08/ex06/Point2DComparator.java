package ch08.ex06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import javafx.geometry.Point2D;

import org.junit.Test;

public class Point2DComparator implements Comparator<Point2D> {

    @Override
    public int compare(Point2D a, Point2D b) {
        double ax = a.getX();
        double ay = a.getY();
        double bx = b.getX();
        double by = b.getY();

        if (ax == bx && ay == by) {
            return 0;
        }
        return Double.compare(ax * ax + ay * ay, bx * bx + by * by);
    }

    @Test
    public void testEqual() {
        Point2D a = new Point2D(1, 2);
        Point2D b = new Point2D(1, 2);
        assertEquals(0, compare(a, b));
    }

    @Test
    public void testGreaterThan() {
        Point2D a = new Point2D(1, 3);
        Point2D b = new Point2D(1, 2);
        assertTrue(0 <= compare(a, b));
    }

    @Test
    public void testLessThan() {
        Point2D a = new Point2D(0, 2);
        Point2D b = new Point2D(1, 2);
        assertTrue(0 >= compare(a, b));
    }
}