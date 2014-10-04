package ch02.ex07;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import org.junit.Test;

public class OrderFromBoss {

    // このメソッドはあまりよくない。
    // spliterator()が終端操作であるため、
    // 未使用のストリームを終了させてしまう or 使用済みのストリームでエラーになってしまう。
    // また、estimateSize()の結果がLong.MAX_VALUEだったとしても、
    // 必ずしも無限ストリームであるわけではない。
    public static <T> boolean isFinite(Stream<T> stream) {
        return stream.spliterator().estimateSize() != Long.MAX_VALUE;
    }

    @Test
    public void testIsFinite() {
        Stream<Double> finite = Stream.of(0.1, 0.2, 0.3);
        assertTrue(isFinite(finite));
        finite.count();

        Stream<Double> infinite = Stream.generate(Math::random);
        assertFalse(isFinite(infinite));
    }
}
