package ch03.ex16;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import org.junit.Test;

public class HandlingException {

    // 3つめのパラメータは、もしsecondが例外を投げるのであれば必要。
    public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second) {
        Thread t = new Thread() {
            public void run() {
                try {
                    T result = first.get();
                    second.accept(result, null);
                } catch (Throwable e) {
                    second.accept(null, e);
                }
            }
        };
        t.start();
    }

    @Test
    public void testNormal() {
        Stack<String> stack = new Stack<>();
        stack.push("1");
        stack.push("2");

        doInOrderAsync(() -> stack.pop(), (result, e) -> {
            assertEquals("2", result);
            assertNull(e);
        });
    }

    @Test
    public void testException() {
        Stack<String> stack = new Stack<>();
        // empty stack

        doInOrderAsync(() -> stack.pop(), (result, e) -> {
            assertNull(result);
            assertTrue(e instanceof EmptyStackException);
        });
    }
}