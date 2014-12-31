package ch06.ex01;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

import org.junit.Test;

public class MaxLengthString {

    private AtomicReference<String> current;

    public MaxLengthString() {
        current = new AtomicReference<>("");
    }

    public void tryUpdate(String value) {
        current.accumulateAndGet(value, (current, income) -> {
            return current.length() > income.length() ? current : income;
        });
    }

    public String get() {
        return current.get();
    }

    @Test
    public void test() throws Exception {
        MaxLengthString app = new MaxLengthString();
        assertEquals("", app.get());

        List<String> values = IntStream.rangeClosed(1, 1000).mapToObj(String::valueOf).collect(toList());
        Collections.shuffle(values);

        ExecutorService ex = Executors.newCachedThreadPool();

        values.stream().forEach(v -> {
            ex.submit(() -> {
                app.tryUpdate(v);
            });
        });

        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.MINUTES);
        assertEquals("1000", app.get());
    }
}
