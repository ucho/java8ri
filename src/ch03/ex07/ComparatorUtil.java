package ch03.ex07;

import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

public class ComparatorUtil {

    public static Comparator<String> stringComparator(boolean inverse, boolean ignoreCase, boolean ignoreWhiteSpace) {
        return (a, b) -> {
            if (ignoreWhiteSpace) {
                a = a.replaceAll(" ", "");
                b = b.replaceAll(" ", "");
            }

            int result;

            if (ignoreCase) {
                result = a.compareToIgnoreCase(b);
            } else {
                result = a.compareTo(b);
            }

            if (inverse) {
                result = -result;
            }

            return result;
        };
    }

    @Test
    public void testNormal() {
        Comparator<String> target = stringComparator(false, false, false);
        assertTrue(target.compare("a", "b") < 0);
        assertTrue(target.compare("a", "a") == 0);
        assertTrue(target.compare("b", "a") > 0);

        assertTrue(target.compare("a", "A") > 0);
        assertTrue(target.compare("abc", "ab c") > 0);
    }

    @Test
    public void testInverse() {
        Comparator<String> target = stringComparator(true, false, false);
        assertTrue(target.compare("a", "b") > 0);
        assertTrue(target.compare("a", "a") == 0);
        assertTrue(target.compare("b", "a") < 0);
    }

    @Test
    public void testIgnoreCase() {
        Comparator<String> target = stringComparator(false, true, false);
        assertTrue(target.compare("A", "b") < 0);
        assertTrue(target.compare("A", "a") == 0);
        assertTrue(target.compare("b", "A") > 0);
    }

    @Test
    public void testIgnoreWhiteSpace() {
        Comparator<String> target = stringComparator(false, false, true);
        assertTrue(target.compare("ab c", "abd") < 0);
        assertTrue(target.compare("abc", "ab c") == 0);
        assertTrue(target.compare("abd", "ab c") > 0);
    }

    @Test
    public void testInverseAndIgnoreCase() {
        Comparator<String> target = stringComparator(true, true, false);
        assertTrue(target.compare("A", "b") > 0);
        assertTrue(target.compare("A", "a") == 0);
        assertTrue(target.compare("b", "A") < 0);
    }

    @Test
    public void testIgnoreCaseAndIgnoreWhiteSpace() {
        Comparator<String> target = stringComparator(false, true, true);
        assertTrue(target.compare("ab d", "Abc") > 0);
        assertTrue(target.compare("ab C", "aBc") == 0);
        assertTrue(target.compare("Abc", "ab d") < 0);
    }

    @Test
    public void testInverseAndIgnoreWhiteSpace() {
        Comparator<String> target = stringComparator(true, false, true);
        assertTrue(target.compare("ab c", "abd") > 0);
        assertTrue(target.compare("abc", "ab c") == 0);
        assertTrue(target.compare("abd", "ab c") < 0);
    }
}
