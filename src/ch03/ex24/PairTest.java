package ch03.ex24;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.Test;

public class PairTest {
    @Test
    public void testFlatMap() {
        Pair<String> target = new Pair<>("first", "second");
        Function<String, Stream<Boolean>> mapper = (t) -> {
            List<Boolean> result = new ArrayList<>(t.length());
            for (char c : t.toCharArray()) {
                result.add(c > 'm');
            }
            return result.stream();
        };
        Stream<Boolean> result = target.flatMap(mapper);
        Object[] expected = { false, false, true, true, true, true, false, false, true, true, false };
        assertArrayEquals(expected, result.toArray());
    }

}
