package ch05.ex07;

import static java.time.LocalDateTime.of;
import static java.time.LocalDateTime.now;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;

import org.junit.Test;

public class TimeIntervalTest {

    @Test
    public void canCreateWithOrdinaryStartAndEndInSameDate() {
        LocalDateTime start = of(2014, 11, 7, 12, 0);
        LocalDateTime end = of(2014, 11, 7, 16, 30);
        new TimeInterval(start, end);
    }

    @Test
    public void canCreateWithSameStartAndEnd() {
        LocalDateTime at = of(2014, 11, 7, 12, 0);
        new TimeInterval(at, at);
    }

    @Test
    public void canCreateWithOrdinaryStartAndEndInDifferentDate() {
        LocalDateTime start = of(2014, 11, 7, 12, 0);
        LocalDateTime end = of(2014, 12, 6, 22, 0);
        new TimeInterval(start, end);
    }

    @Test
    public void cannotCreateWithEndBeforeStart() {
        LocalDateTime start = of(2014, 11, 7, 12, 0);
        LocalDateTime end = of(2014, 11, 7, 9, 0);
        try {
            new TimeInterval(start, end);
            fail();
        } catch (IllegalArgumentException e) {
            // success
        }
    }

    @Test
    public void cannotCreateWithNull() {
        try {
            new TimeInterval(null, null);
            fail();
        } catch (NullPointerException e) {
            // success
        }

        try {
            new TimeInterval(null, now());
            fail();
        } catch (NullPointerException e) {
            // success
        }

        try {
            new TimeInterval(now(), null);
            fail();
        } catch (NullPointerException e) {
            // success
        }
    }

    @Test
    public void throwExceptionWithNullOne() {
        TimeInterval t = new TimeInterval(now(), now());
        try {
            t.conflictingWith(null);
            fail();
        } catch (NullPointerException e) {
            // success
        }
    }

    @Test
    public void notConflictWithBeforeOne() {
        TimeInterval base = new TimeInterval(
                of(2014, 11, 7, 12, 0),
                of(2014, 11, 7, 13, 0));
        TimeInterval other = new TimeInterval(
                of(2014, 11, 7, 11, 58),
                of(2014, 11, 7, 11, 59));
        assertFalse(base.conflictingWith(other));
    }

    @Test
    public void notConflictWithEndAtStart() {
        TimeInterval base = new TimeInterval(
                of(2014, 11, 7, 12, 0),
                of(2014, 11, 7, 13, 0));
        TimeInterval other = new TimeInterval(
                of(2014, 11, 7, 11, 59),
                of(2014, 11, 7, 12, 00));
        assertFalse(base.conflictingWith(other));
    }

    @Test
    public void notConflictWithAtStartPoint() {
        TimeInterval base = new TimeInterval(
                of(2014, 11, 7, 12, 0),
                of(2014, 11, 7, 13, 0));
        TimeInterval other = new TimeInterval(
                of(2014, 11, 7, 12, 0),
                of(2014, 11, 7, 12, 0));
        assertFalse(base.conflictingWith(other));
    }

    @Test
    public void conflictWithEndAfterStart() {
        TimeInterval base = new TimeInterval(
                of(2014, 11, 7, 12, 0),
                of(2014, 11, 7, 13, 0));
        TimeInterval other = new TimeInterval(
                of(2014, 11, 7, 12, 0),
                of(2014, 11, 7, 12, 1));
        assertTrue(base.conflictingWith(other));
    }

    @Test
    public void conflictWithSameOne() {
        TimeInterval base = new TimeInterval(
                of(2014, 11, 7, 12, 0),
                of(2014, 11, 7, 13, 0));
        TimeInterval other = new TimeInterval(
                of(2014, 11, 7, 12, 0),
                of(2014, 11, 7, 13, 0));
        assertTrue(base.conflictingWith(other));
    }

    @Test
    public void conflictWithIncludedOne() {
        TimeInterval base = new TimeInterval(
                of(2014, 11, 7, 12, 0),
                of(2014, 11, 7, 13, 0));
        TimeInterval other = new TimeInterval(
                of(2014, 11, 7, 12, 01),
                of(2014, 11, 7, 12, 59));
        assertTrue(base.conflictingWith(other));
    }

    @Test
    public void conflictWithCoveringOne() {
        TimeInterval base = new TimeInterval(
                of(2014, 11, 7, 12, 0),
                of(2014, 11, 7, 13, 0));
        TimeInterval other = new TimeInterval(
                of(2014, 11, 7, 11, 59),
                of(2014, 11, 7, 13, 01));
        assertTrue(base.conflictingWith(other));
    }

    @Test
    public void conflictWithStartBeforeEnd() {
        TimeInterval base = new TimeInterval(
                of(2014, 11, 7, 12, 0),
                of(2014, 11, 7, 13, 0));
        TimeInterval other = new TimeInterval(
                of(2014, 11, 7, 12, 59),
                of(2014, 11, 7, 13, 00));
        assertTrue(base.conflictingWith(other));
    }

    @Test
    public void notConflictWithStartAtEnd() {
        TimeInterval base = new TimeInterval(
                of(2014, 11, 7, 12, 0),
                of(2014, 11, 7, 13, 0));
        TimeInterval other = new TimeInterval(
                of(2014, 11, 7, 13, 0),
                of(2014, 11, 7, 13, 1));
        assertFalse(base.conflictingWith(other));
    }

    @Test
    public void notConflictWithAtEndPoint() {
        TimeInterval base = new TimeInterval(
                of(2014, 11, 7, 12, 0),
                of(2014, 11, 7, 13, 0));
        TimeInterval other = new TimeInterval(
                of(2014, 11, 7, 13, 0),
                of(2014, 11, 7, 13, 0));
        assertFalse(base.conflictingWith(other));
    }

    @Test
    public void notConflictWithAfterOne() {
        TimeInterval base = new TimeInterval(
                of(2014, 11, 7, 12, 0),
                of(2014, 11, 7, 13, 0));
        TimeInterval other = new TimeInterval(
                of(2014, 11, 7, 13, 0),
                of(2014, 11, 7, 13, 1));
        assertFalse(base.conflictingWith(other));
    }
}
