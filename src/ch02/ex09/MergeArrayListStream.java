package ch02.ex09;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;

public class MergeArrayListStream {

    public static <T> ArrayList<T> merge1(Stream<ArrayList<T>> target) {
        Optional<ArrayList<T>> result = target.reduce((x, y) -> {
            x.addAll(y);
            return x;
        });
        return result.isPresent() ? result.get() : null;
    }

    public static <T> ArrayList<T> merge2(Stream<ArrayList<T>> target) {
        return target.reduce(new ArrayList<T>(), (x, y) -> {
            x.addAll(y);
            return x;
        });
    }

    public static <T> ArrayList<T> merge3(Stream<ArrayList<T>> target) {
        return target.reduce(new ArrayList<T>(), (x, y) -> {
            x.addAll(y);
            return x;
        }, (a, b) -> {
            a.addAll(b);
            return a;
        });
    }

    private static Stream<ArrayList<String>> createTarget() {
        ArrayList<String> l1 = new ArrayList<>();
        l1.add("1");
        l1.add("2");

        ArrayList<String> l2 = new ArrayList<>();
        l2.add("3");
        l2.add("4");

        ArrayList<String> l3 = new ArrayList<>();
        l3.add("5");
        l3.add("6");

        return Stream.of(l1, l2, l3);
    }

    private static String[] createExpected() {
        String[] expected = { "1", "2", "3", "4", "5", "6" };
        return expected;
    }

    @Test
    public void testMerge1() {
        ArrayList<String> result = merge1(createTarget());
        String[] expected = createExpected();
        assertArrayEquals(result.toArray(), expected);
    }

    @Test
    public void testMerge2() {
        ArrayList<String> result = merge2(createTarget());
        String[] expected = createExpected();
        assertArrayEquals(result.toArray(), expected);
    }

    @Test
    public void testMerge3() {
        ArrayList<String> result = merge3(createTarget());
        String[] expected = createExpected();
        assertArrayEquals(result.toArray(), expected);
    }
}
