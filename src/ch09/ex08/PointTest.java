package ch09.ex08;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

class Point implements Comparable<Point> {

    final private int x;
    final private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point other) {
        if (x < other.x) {
            return -1;
        }
        if (x > other.x) {
            return 1;
        }
        if (y < other.y) {
            return -1;
        }
        if (y > other.y) {
            return 1;
        }
        return 0;
    }
}

public class PointTest {

    @Test
    public void testCompareTo() {
        assertTrue(0 > new Point(2, 3).compareTo(new Point(3, 2)));
        assertTrue(0 < new Point(3, 2).compareTo(new Point(2, 3)));
        assertTrue(0 > new Point(2, 2).compareTo(new Point(2, 3)));
        assertTrue(0 < new Point(2, 3).compareTo(new Point(2, 2)));
        assertTrue(0 == new Point(2, 3).compareTo(new Point(2, 3)));
    }
}
