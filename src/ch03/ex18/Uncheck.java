package ch03.ex18;

import static org.junit.Assert.assertEquals;

import java.util.function.Function;

import org.junit.Test;

@FunctionalInterface
interface FunctionWithException<T, U> {
    U apply(T t) throws Exception;
}

public class Uncheck {
    public static <T, U> Function<T, U> unchecked(FunctionWithException<T, U> f) {
        return (t) -> {
            try {
                return f.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } catch (Throwable e) {
                throw e;
            }
        };
    }

    @Test
    public void testUnchecked() {
        try {
            unchecked((String t) -> {
                throw new Exception(t);
            }).apply("hoge");
        } catch (RuntimeException e) {
            assertEquals("hoge", e.getCause().getMessage());
        }
    }
}
