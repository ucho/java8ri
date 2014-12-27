package ch06.ex04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;

import org.junit.Test;

public class LongAccumulatorTest {

    @Test
    public void testGetMax() throws Exception {
        LongAccumulator accm = new LongAccumulator(Math::max, 0);
        ExecutorService ex = Executors.newCachedThreadPool();
        for (int i = 10; i > 0; i--) {
            long v = i;
            ex.submit(() -> {
                accm.accumulate(v);
            });
        }
        ex.shutdown();
        assertTrue(ex.awaitTermination(1, TimeUnit.MINUTES));
        assertEquals(10, accm.get());
    }

    @Test
    public void testGetMin() throws Exception {
        LongAccumulator accm = new LongAccumulator(Math::min, 0);
        ExecutorService ex = Executors.newCachedThreadPool();
        for (int i = -5; i < 5; i++) {
            long v = i;
            ex.submit(() -> {
                accm.accumulate(v);
            });
        }
        ex.shutdown();
        assertTrue(ex.awaitTermination(1, TimeUnit.MINUTES));
        assertEquals(-5, accm.get());
    }

}
