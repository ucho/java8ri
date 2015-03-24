package ch09.ex09;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Objects;

import org.junit.Test;

class LabeledPoint {

    final private String label;
    final private int x;
    final private int y;

    public LabeledPoint(String label, int x, int y) {
        this.label = label;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (getClass() != otherObject.getClass()) {
            return false;
        }
        LabeledPoint other = (LabeledPoint) otherObject;
        return Objects.equals(label, other.label) &&
                Objects.equals(x, other.x) &&
                Objects.equals(y, other.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, x, y);
    }
}

public class LabeledPointTest {

    @Test
    public void testEquals() {
        LabeledPoint a = new LabeledPoint("a", 1, 2);
        assertTrue(a.equals(a));
        assertFalse(a.equals(null));
        assertFalse(a.equals("a"));

        LabeledPoint b = new LabeledPoint("a", 2, 3);
        LabeledPoint c = new LabeledPoint("a", 1, 2);
        assertFalse(a.equals(b));
        assertTrue(a.equals(c));
    }

    @Test
    public void testHashCode() {
        LabeledPoint a = new LabeledPoint("a", 1, 2);
        LabeledPoint b = new LabeledPoint("a", 2, 3);
        LabeledPoint c = new LabeledPoint("a", 1, 2);
        assertNotEquals(a.hashCode(), b.hashCode());
        assertEquals(a.hashCode(), c.hashCode());
    }
}
