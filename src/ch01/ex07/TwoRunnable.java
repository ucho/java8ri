package ch01.ex07;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TwoRunnable {

    public static Runnable andThen(Runnable first, Runnable second) {
        return () -> {
            first.run();
            second.run();
        };
    }

    @Test
    public void testAndThen() throws Exception {
        List<String> result = new ArrayList<>(2);
        Runnable first = () -> {
            result.add("first executed!");
        };
        Runnable second = () -> {
            result.add("second executed!");
        };
        Thread th = new Thread(andThen(first, second));
        th.start();
        th.join();

        assertEquals(result.get(0), "first executed!");
        assertEquals(result.get(1), "second executed!");
    }
}
