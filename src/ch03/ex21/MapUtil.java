package ch03.ex21;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import org.junit.Test;

public class MapUtil {

    public static <T, U> Future<U> map(Future<T> target, Function<T, U> f) {
        return new Future<U>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return target.cancel(mayInterruptIfRunning);
            }

            @Override
            public boolean isCancelled() {
                return target.isCancelled();
            }

            @Override
            public boolean isDone() {
                return target.isDone();
            }

            @Override
            public U get() throws InterruptedException, ExecutionException {
                return f.apply(target.get());
            }

            @Override
            public U get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return f.apply(target.get(timeout, unit));
            }
        };
    }

    @Test
    public void testMap() throws Exception {
        ExecutorService ex = Executors.newSingleThreadExecutor();
        Future<Integer> target = ex.submit(() -> 1);
        Future<String> result = map(target, (t) -> t.toString());
        assertEquals("1", result.get());
    }
}
