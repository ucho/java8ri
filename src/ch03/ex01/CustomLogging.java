package ch03.ex01;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

public class CustomLogging {

    // テストをしやすくするためログに書いた値をreturnするようにしている
    public static String logIf(Level level, Supplier<Boolean> filter, Supplier<String> msgSupplier) {
        if (filter.get()) {
            Logger.getGlobal().log(level, msgSupplier);
            return msgSupplier.get();
        }
        return "";
    }

    @Test
    public void testLogIf() {
        String[] a = new String[11];
        Arrays.fill(a, "normal");
        a[10] = "special";

        String[] result = new String[11];
        for (int n = 0; n < 11; n++) {
            int i = n;
            result[n] = logIf(Level.FINEST, () -> i == 10, () -> "a[10] = " + a[10]);
        }

        for (int n = 0; n < 10; n++) {
            assertEquals("", result[n]);
        }
        assertEquals("a[10] = special", result[10]);
    }
}
