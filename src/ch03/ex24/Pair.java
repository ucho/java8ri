package ch03.ex24;

import java.util.function.Function;
import java.util.stream.Stream;

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

    public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
        return Stream.concat(mapper.apply(first), mapper.apply(second));
    }
}
