package ch03.ex23;

import static org.junit.Assert.assertArrayEquals;

import java.util.function.Function;

import org.junit.Test;

public class Pair<T> {

    private T first;
    private T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public Object[] toArray() {
        Object[] array = { first, second };
        return array;
    }

    public static <U, R> Pair<R> map(Pair<U> target, Function<U, R> f) {
        return new Pair<R>(f.apply(target.getFirst()), f.apply(target.getSecond()));
    }

    @Test
    public void testMap() {
        Pair<String> target = new Pair<String>("first", "second");
        Function<String, Boolean> f = (t) -> t.length() > 5;
        Pair<Boolean> result = map(target, f);
        Object[] expected = { false, true };
        assertArrayEquals(expected, result.toArray());
    }
}
