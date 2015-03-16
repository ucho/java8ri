package ch08.ex06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import javafx.geometry.Rectangle2D;

import org.junit.Test;

public class Rectangle2DComparator implements Comparator<Rectangle2D> {

    @Override
    public int compare(Rectangle2D a, Rectangle2D b) {
        double ah = a.getHeight();
        double aw = a.getWidth();
        double bh = b.getHeight();
        double bw = b.getWidth();

        if (ah == bh && aw == bw) {
            return 0;
        }
        return Double.compare(ah * aw, bh * bw);
    }

    @Test
    public void testEqual() {
        Rectangle2D a = new Rectangle2D(1, 2, 3, 4);
        Rectangle2D b = new Rectangle2D(1, 2, 3, 4);
        assertEquals(0, compare(a, b));
    }

    @Test
    public void testGreaterThan() {
        Rectangle2D a = new Rectangle2D(0, 2, 3, 4);
        Rectangle2D b = new Rectangle2D(1, 2, 3, 4);
        assertTrue(0 <= compare(a, b));
    }

    @Test
    public void testLessThan() {
        Rectangle2D a = new Rectangle2D(1, 3, 3, 4);
        Rectangle2D b = new Rectangle2D(1, 2, 3, 4);
        assertTrue(0 >= compare(a, b));
    }
}
