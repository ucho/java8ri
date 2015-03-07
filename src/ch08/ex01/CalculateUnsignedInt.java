package ch08.ex01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CalculateUnsignedInt {

    @Test
    public void add() {
        assertEquals(2147483648L, Integer.toUnsignedLong(Integer.MAX_VALUE) + 1);
    }

    @Test
    public void subtract() {
        assertEquals(2147483647, Integer.toUnsignedLong(Integer.MIN_VALUE) - 1);
    }

    @Test
    public void multiply() {
        assertEquals(4294967294L, Integer.toUnsignedLong(Integer.MAX_VALUE) * 2);
    }

    @Test
    public void divide() {
        assertEquals(Integer.MAX_VALUE, Integer.divideUnsigned(Integer.MAX_VALUE, 1));
    }

    @Test
    public void compare() {
        assertTrue(Integer.compareUnsigned(1, 1) == 0);
        assertTrue(Integer.compareUnsigned(1, 0) > 0);
        assertTrue(Integer.compareUnsigned(0, 1) < 0);
    }
}
