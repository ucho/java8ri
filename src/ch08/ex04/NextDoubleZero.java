package ch08.ex04;

import static org.junit.Assert.assertEquals;

import java.util.Random;
import java.util.stream.Stream;

import org.junit.Test;

public class NextDoubleZero {

    private static final long m = 25214903917L;
    private static final long a = 11L;
    private static final long N = (1L << 48) - 1;
    private static final long v = 246154705703781L;

    private static long next(long s) {
        return (s * m + a) & N;
    }

    private static long prev(long s) {
        return ((s - a) * v) & N;
    }

    @Test
    public void validateNextAndPrev() {
        long s = 277363943098L;
        assertEquals(s, prev(next(s)));
    }

    @Test
    public void generateZero() {
        long s = 164311266871034L;
        assertEquals(s, prev(prev(prev(0))) ^ m, 0);
        Random r = new Random(s);
        r.nextDouble();
        assertEquals(0, r.nextDouble(), 0);
    }

    @Test
    public void computeMinSeed() {
        long minS = Stream.iterate(0L, NextDoubleZero::prev)
                .limit(1000001).mapToLong(s -> s ^ m).boxed().min(Long::compare).get();
        System.out.println("minimal seed: " + minS); // 881498
        Random r = new Random(minS);
        for (int i = 0; i < 376050 - 1; i++) {
            r.nextDouble();
        }
        assertEquals(0, r.nextDouble(), 0);
    }
}
