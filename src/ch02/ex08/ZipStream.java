package ch02.ex08;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

public class ZipStream {

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        List<T> result = new ArrayList<>();
        Iterator<T> iter1 = first.iterator();
        Iterator<T> iter2 = second.iterator();
        for (; iter1.hasNext() && iter2.hasNext();) {
            result.add(iter1.next());
            result.add(iter2.next());
        }
        return result.stream();
    }

    @Test
    public void testZip() {
        Stream<Integer> first = Stream.of(1, 3, 5, 7);
        Stream<Integer> second = Stream.of(2, 4, 6, 8, 10);
        Stream<Integer> zipped = zip(first, second);

        Integer[] expected = { 1, 2, 3, 4, 5, 6, 7, 8 };
        Integer[] actual = zipped.toArray(Integer[]::new);
        assertArrayEquals(expected, actual);
    }
}
