package ch02.ex05;

import java.util.stream.Stream;

public class LCG {

    public static Stream<Long> stream(long a, long c, long m, long seed) {
        return Stream.iterate(seed, x -> (a * x + c) % m);
    }

    public static void main(String[] args) {
        stream(25214903917L, 11, (2 ^ 48), 1).limit(10).forEach(
                System.out::println);
    }

}
