package ch06.ex02;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

import org.junit.Test;

// LongAdderはIDを生成するために役立つと考える。
// なぜならばこのようなケースで一般的に用いられるであろうAtomicLongを用いた場合と比較し、
// 8倍程度高速であったためである。
// LongAdderはincrementと値の取得がアトミックではないので、それらをsynchronizedで囲む必要があるため、
// そのような処理を行うメソッドを元々持つAtomicLongの方がシンプルに書けて高速であると予想したが、
// 実際に計測してみると、LongAdderを用いた場合の方が高速であった。
public class IDGeneratorTest {

    @Test
    public void printTimes() throws Exception {
        System.out.println("AtomicLong: " + measure(new IDGeneratorAtomicLong()).toMillis());
        System.out.println("LongAdder: " + measure(new IDGeneratorLongAdder()).toMillis());
    }

    public Duration measure(IDGenerator app) throws Exception {
        Objects.requireNonNull(app);

        ExecutorService ex = Executors.newCachedThreadPool();
        Set<Long> results = Collections.synchronizedSet(new HashSet<>());

        Instant before = Instant.now();
        for (int i = 0; i < 1000; i++) {
            ex.submit(() -> {
                results.add(app.generate());
            });
        }
        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.MINUTES);
        Instant after = Instant.now();

        assertEquals(1000, results.size());
        return Duration.between(before, after);
    }

}

interface IDGenerator {
    long generate();
}

class IDGeneratorLongAdder implements IDGenerator {

    private LongAdder adder;

    public IDGeneratorLongAdder() {
        adder = new LongAdder();
    }

    @Override
    public long generate() {
        long result = 0L;
        synchronized (adder) {
            adder.increment();
            result = adder.longValue();
        }
        return result;
    }
}

class IDGeneratorAtomicLong implements IDGenerator {

    private AtomicLong value;

    public IDGeneratorAtomicLong() {
        value = new AtomicLong();
    }

    @Override
    public long generate() {
        return value.incrementAndGet();
    }
}