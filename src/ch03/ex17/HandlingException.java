package ch03.ex17;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import org.junit.Test;

public class HandlingException {

    public static void doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {
        Callable<Object> c1 = Executors.callable(first);
        Callable<Object> c2 = Executors.callable(second);
        ExecutorService ex = Executors.newFixedThreadPool(2);
        Future<Object> f1 = ex.submit(c1);
        Future<Object> f2 = ex.submit(c2);
        try {
            f1.get();
            f2.get();
        } catch (InterruptedException | ExecutionException e) {
            handler.accept(e);
        }
    }

    @Test
    public void testFirstException() {
        Map<String, Boolean> result = Collections.synchronizedMap(new HashMap<>(2));
        doInParallelAsync(() -> {
            result.put("first", true);
            throw new RuntimeException("first");
        }, () -> {
            result.put("second", true);
        }, (e) -> {
            assertEquals("first", e.getCause().getMessage());
        });
        assertTrue(result.get("first"));
        assertTrue(result.get("second"));
    }

    @Test
    public void testSecondException() {
        Map<String, Boolean> result = Collections.synchronizedMap(new HashMap<>(2));
        doInParallelAsync(() -> {
            result.put("first", true);
        }, () -> {
            result.put("second", true);
            throw new RuntimeException("second");
        }, (e) -> {
            assertEquals("second", e.getCause().getMessage());
        });
        assertTrue(result.get("first"));
        assertTrue(result.get("second"));
    }

    @Test
    public void testHandlerCalledOnce() {
        Map<String, Boolean> result = Collections.synchronizedMap(new HashMap<>(2));
        AtomicInteger count = new AtomicInteger();
        doInParallelAsync(() -> {
            result.put("first", true);
            throw new RuntimeException("first");
        }, () -> {
            result.put("second", true);
            throw new RuntimeException("second");
        }, (e) -> {
            count.incrementAndGet();
        });
        assertTrue(result.get("first"));
        assertTrue(result.get("second"));
        assertEquals(1, count.get());
    }

}
