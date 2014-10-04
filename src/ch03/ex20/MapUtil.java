package ch03.ex20;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

public class MapUtil {

    public static <T, U> List<U> map(List<T> target, Function<T, U> f) {
        return target.stream().map(f).collect(Collectors.toList());
    }

    @Test
    public void testMap() {
        List<String> target = Arrays.asList("1", "2", "3");
        Function<String, Integer> f = (t) -> Integer.parseInt(t);
        List<Integer> result = map(target, f);
        Object[] expect = { 1, 2, 3 };
        assertArrayEquals(expect, result.toArray());
    }
}
