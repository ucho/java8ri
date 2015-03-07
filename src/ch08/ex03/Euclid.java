package ch08.ex03;

import static java.lang.Math.abs;
import static java.lang.Math.floorMod;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Euclid {

    public static int gcdWithPersent(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcdWithPersent(b, abs(a % b));
    }

    public static int gcdWithFloorMod(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcdWithFloorMod(b, floorMod(abs(a), abs(b)));
    }

    public static int gcdWithRem(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcdWithRem(b, rem(a, b));
    }

    private static int rem(int a, int b) {
        return abs(a % b);
    }

    @Test
    public void testPlusBoth() {
        assertEquals(21, Euclid.gcdWithPersent(1071, 1029));
        assertEquals(21, Euclid.gcdWithFloorMod(1071, 1029));
        assertEquals(21, Euclid.gcdWithRem(1071, 1029));
    }

    @Test
    public void testMinusA() {
        assertEquals(21, Euclid.gcdWithPersent(-1071, 1029));
        assertEquals(21, Euclid.gcdWithFloorMod(-1071, 1029));
        assertEquals(21, Euclid.gcdWithRem(-1071, 1029));
    }

    @Test
    public void testMinusB() {
        assertEquals(21, Euclid.gcdWithPersent(1071, -1029));
        assertEquals(21, Euclid.gcdWithFloorMod(1071, -1029));
        assertEquals(21, Euclid.gcdWithRem(1071, -1029));
    }

    @Test
    public void testMinusBoth() {
        assertEquals(21, Euclid.gcdWithPersent(-1071, -1029));
        assertEquals(21, Euclid.gcdWithFloorMod(-1071, -1029));
        assertEquals(21, Euclid.gcdWithRem(-1071, -1029));
    }
}
