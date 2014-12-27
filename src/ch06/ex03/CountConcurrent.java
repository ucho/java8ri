package ch06.ex03;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

import org.junit.Test;

// 内山のPCで実行した結果(試行回数5回の平均値)
//   AtomicLong: 3031.8ms
//   LongAdder: 2335.2ms
// LongAdderの方が速い。
public class CountConcurrent {

    private final int NUM_THREAD = 1000;
    private final int NUM_COUNT = 100000;
    private final int NUM_TRY = 5;

    @Test
    public void countOnAtomicLong() throws Exception {
        AtomicLong count = new AtomicLong();
        List<Callable<Object>> tasks = new ArrayList<>(NUM_THREAD);
        for (int i = 0; i < NUM_THREAD; i++) {
            tasks.add(() -> {
                for (int j = 0; j < NUM_COUNT; j++) {
                    count.incrementAndGet();
                }
                return null;
            });
        }
        assertEquals(NUM_THREAD, tasks.size());
        double result = calcurateAverageMillis(invokeAndMeasure(tasks, NUM_TRY));
        System.out.println("count on AtomicLong: " + result + "ms");
        assertEquals(NUM_THREAD * NUM_COUNT * NUM_TRY, count.get());
    }

    @Test
    public void countOnLongAdder() throws Exception {
        LongAdder adder = new LongAdder();
        List<Callable<Object>> tasks = new ArrayList<>(NUM_THREAD);
        for (int i = 0; i < NUM_THREAD; i++) {
            tasks.add(() -> {
                for (int j = 0; j < NUM_COUNT; j++) {
                    adder.increment();
                }
                return null;
            });
        }
        assertEquals(NUM_THREAD, tasks.size());
        double result = calcurateAverageMillis(invokeAndMeasure(tasks, NUM_TRY));
        System.out.println("count on LongAdder: " + result + "ms");
        assertEquals(NUM_THREAD * NUM_COUNT * NUM_TRY, adder.longValue());
    }

    private List<Duration> invokeAndMeasure(List<Callable<Object>> tasks, int n) throws Exception {
        List<Duration> result = new ArrayList<>(n);
        ExecutorService ex = Executors.newFixedThreadPool(tasks.size());
        for (int i = 0; i < n; i++) {
            Instant before = Instant.now();
            ex.invokeAll(tasks);
            Instant after = Instant.now();
            result.add(Duration.between(before, after));
        }
        return result;
    }

    private double calcurateAverageMillis(List<Duration> result) {
        return result.stream().mapToLong(Duration::toMillis).average().getAsDouble();
    }
}
