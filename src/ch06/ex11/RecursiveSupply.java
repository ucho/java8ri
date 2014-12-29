package ch06.ex11;

import static org.junit.Assert.assertEquals;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

public class RecursiveSupply {

    public static <T> CompletableFuture<T> repeat(Supplier<T> action, Predicate<T> until) {
        Objects.requireNonNull(action);
        Objects.requireNonNull(until);
        return CompletableFuture.supplyAsync(action)
                .thenComposeAsync(t -> until.test(t) ?
                        CompletableFuture.completedFuture(t) : repeat(action, until));
    }

    @Test
    public void testRepeat() throws Exception {
        String[] data = { "foo", "bar", "hoge", "secret" };
        AtomicInteger count = new AtomicInteger();
        String result = repeat(() -> {
            return data[count.getAndIncrement()];
        }, p -> {
            return p.equals("secret");
        }).get();
        assertEquals(4, count.get());
        assertEquals("secret", result);
    }
}
