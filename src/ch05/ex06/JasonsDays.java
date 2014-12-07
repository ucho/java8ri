package ch05.ex06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class JasonsDays {

    public List<LocalDate> calcurate(int fromYear, int untilYear) {
        if (fromYear < 0 || untilYear < 0) {
            throw new IllegalArgumentException("fromYear & untilYear must not be minus.");
        }
        if (fromYear > untilYear) {
            throw new IllegalArgumentException("fromYear is after untilYear.");
        }
        List<LocalDate> result = new ArrayList<>();
        LocalDate end = LocalDate.of(untilYear, 12, 31);
        LocalDate w = LocalDate.of(fromYear, 1, 13);
        while (w.isBefore(end)) {
            if (w.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                result.add(w);
            }
            w = w.plusMonths(1);
        }
        return result;
    }

    @Test
    public void testCalcurateAllDaysOf20thCentury() {
        List<LocalDate> result = calcurate(1901, 2000);
        int count = result.size();
        assertTrue(100 < count && count < 200); // 年に1回以上2回以下のはず
        result.stream().forEach(d -> {
            assertEquals(13, d.getDayOfMonth());
            assertEquals(DayOfWeek.FRIDAY, d.getDayOfWeek());
            System.out.println(d);
        });
    }

    @Test
    public void testIllegalArgument() {
        try {
            calcurate(-1, 0);
            fail();
        } catch (IllegalArgumentException e) {
            // success
        }

        try {
            calcurate(1900, 1899);
            fail();
        } catch (IllegalArgumentException e) {
            // success
        }
    }
}
