package ch09.ex10;

import static org.junit.Assert.assertTrue;

import java.util.Objects;

import org.junit.Test;

class LabeledPoint implements Comparable<LabeledPoint> {

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

    @Override
    public int compareTo(LabeledPoint other) {
        int diffLabel = label.compareTo(other.label);
        if (diffLabel != 0) {
            return diffLabel;
        }
        int diffX = Integer.compare(x, other.x);
        if (diffX != 0) {
            return diffX;
        }
        return Integer.compare(y, other.y);
    }
}

public class LabeledPointTest {

    @Test
    public void testCompareTo() {
        assertTrue(0 > new LabeledPoint("a", 2, 3).compareTo(new LabeledPoint("b", 2, 3)));
        assertTrue(0 > new LabeledPoint("a", 2, 3).compareTo(new LabeledPoint("a", 3, 3)));
        assertTrue(0 > new LabeledPoint("a", 2, 2).compareTo(new LabeledPoint("a", 2, 3)));
        assertTrue(0 == new LabeledPoint("a", 2, 3).compareTo(new LabeledPoint("a", 2, 3)));
    }
}
