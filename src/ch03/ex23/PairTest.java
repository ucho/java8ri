package ch03.ex23;

import static org.junit.Assert.assertArrayEquals;

import java.util.function.Function;

import org.junit.Test;

public class PairTest {

    @Test
    public void testMap() {
        Pair<String> target = new Pair<>("first", "second");
        Function<String, Boolean> f = (t) -> t.length() > 5;
        Pair<Boolean> result = Pair.map(target, f);
        Object[] expected = { false, true };
        assertArrayEquals(expected, result.toArray());
    }
}
