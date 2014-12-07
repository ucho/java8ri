package ch05.ex03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Test;

public class TemporalAdjustorUtil {

    public static int NEXT_RANGE = 365;

    public static TemporalAdjuster next(Predicate<LocalDate> target) {
        return TemporalAdjusters.ofDateAdjuster(w -> {
            LocalDate result = w;
            int count = 0;
            do {
                result = result.plusDays(1);
                count++;
            } while (!target.test(result) && count < NEXT_RANGE);
            if (count == NEXT_RANGE) {
                throw new IllegalArgumentException("over next range.");
            }
            return result;
        });
    }

    @Test
    public void testNextDayOfWeek() {
        Arrays.stream(DayOfWeek.values()).forEach(d -> {
            LocalDate today = LocalDate.now().with(ChronoField.DAY_OF_WEEK, d.getValue());

            LocalDate expect = null;
            switch (today.getDayOfWeek()) {
            case SUNDAY:
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
                expect = today.plusDays(1);
                break;
            case FRIDAY:
                expect = today.plusDays(3);
                break;
            case SATURDAY:
                expect = today.plusDays(2);
                break;
            default:
                fail();
            }

            LocalDate actual = today.with(next(w -> w.getDayOfWeek().getValue() < 6));

            assertEquals(expect, actual);
        });
    }

    @Test
    public void testNextNotFoundDay() {
        try {
            LocalDate.now().with(next(w -> w.getDayOfYear() == 367));
            fail("no exception is caught.");
        } catch (IllegalArgumentException e) {
            // success
        }
    }
}
