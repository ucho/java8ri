package ch05.ex01;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;

import org.junit.Test;

public class ProgrammersDay {

    // (2^8-1)日のDurationを求めて、元日に足す
    public static LocalDate getWithoutPlusDays() {
        Duration days = Duration.ofDays(1);
        for (int i = 0; i < 8; i++) {
            days = days.multipliedBy(2);
        }
        days = days.minusDays(1);
        long start = LocalDate.of(2014, 1, 1).toEpochDay();
        return LocalDate.ofEpochDay(start + days.toDays());
    }

    @Test
    public void testGetWithoutPlusDays() {
        LocalDate expected = LocalDate.of(2014, 1, 1).plusDays(255);
        LocalDate actual = getWithoutPlusDays();
        assertEquals(expected, actual);
    }

}
