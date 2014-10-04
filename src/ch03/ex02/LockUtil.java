package ch03.ex02;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

import org.junit.Test;

public class LockUtil {

    public static <T> T withLock(ReentrantLock lock, Supplier<T> proc) {
        lock.lock();
        try {
            return proc.get();
        } finally {
            lock.unlock();
        }
    }

    public static void withLock(ReentrantLock lock, Runnable proc) {
        lock.lock();
        try {
            proc.run();
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void testWithLock() {
        ReentrantLock myLock = new ReentrantLock();

        int[] value = new int[1];
        withLock(myLock, () -> value[0] = 1);
        assertEquals(1, value[0]);

        assertEquals("hoge", withLock(myLock, () -> "hoge"));
    }
}
