package ch05.ex05;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class MyLivingDays {

    public static long calcurateDays(LocalDate from) {
        LocalDate now = LocalDate.now();
        if (from.isAfter(now)) {
            throw new IllegalArgumentException("specified date is future.");
        }
        return from.until(now, ChronoUnit.DAYS);
    }

    @Test
    public void testCalcurateDays() {
        LocalDate myBirthday = LocalDate.of(1981, 10, 4);
        long result = calcurateDays(myBirthday);
        System.out.println("my living days = " + result);
        assertTrue(result > 0);
    }

    @Test
    public void testFutureDate() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        try {
            calcurateDays(tomorrow);
            fail();
        } catch (IllegalArgumentException e) {
            // success
        }
    }
}
