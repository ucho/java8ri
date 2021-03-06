package ch03.ex23;

import java.util.function.Function;

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
}