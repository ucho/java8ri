package ch02.ex10;

import static org.junit.Assert.assertEquals;

import java.util.stream.Stream;

import org.junit.Test;

public class CalcAverageOfDoubles {

    // 単純に合計を計算して、count()で割ることができない理由：
    // 合計の計算も、count()も、どちらも終端操作であるため。
    public static double calcAverage(Stream<Double> target) {
        int[] count = { 0 };
        double sum = target.reduce(0.0, (current, next) -> {
            count[0]++;
            return current + next;
        });
        return sum / count[0];
    }

    @Test
    public void testCalcAverage() {
        assertEquals(3.5, calcAverage(Stream.of(1.0, 3.0, 4.2, 5.8)), 0);
    }
}
