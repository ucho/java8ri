package ch05.ex12;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ReminderTest {

    private Reminder reminder;

    @Before
    public void setUp() {
        reminder = new Reminder();
    }

    @Test
    public void invalidArgument() {
        try {
            reminder.addPromise(null, ZonedDateTime.now());
            fail();
        } catch (IllegalArgumentException e) {
            // pass
        }

        try {
            reminder.addPromise("", ZonedDateTime.now());
            fail();
        } catch (IllegalArgumentException e) {
            // pass
        }

        try {
            reminder.addPromise("title", null);
            fail();
        } catch (IllegalArgumentException e) {
            // pass
        }

        try {
            reminder.remind(null);
            fail();
        } catch (IllegalArgumentException e) {
            // pass
        }
    }

    @Test
    public void testRestMinutes() {
        reminder.addPromise("1h1m-after", ZonedDateTime.of(LocalDateTime.of(2014, 12, 25, 11, 1), ZoneId.of("UTC+9")));
        reminder.addPromise("1h-after", ZonedDateTime.of(LocalDateTime.of(2014, 12, 25, 11, 0), ZoneId.of("UTC+9")));
        reminder.addPromise("59m-after", ZonedDateTime.of(LocalDateTime.of(2014, 12, 25, 10, 59), ZoneId.of("UTC+9")));
        reminder.addPromise("ontime", ZonedDateTime.of(LocalDateTime.of(2014, 12, 25, 10, 0), ZoneId.of("UTC+9")));
        reminder.addPromise("1m-before", ZonedDateTime.of(LocalDateTime.of(2014, 12, 25, 9, 59), ZoneId.of("UTC+9")));

        List<Notification> results = reminder.remind(ZonedDateTime.of(2014, 12, 25, 10, 0, 0, 0, ZoneId.of("UTC+9")));

        assertEquals(3, results.size());
        assertEquals("1h-after", results.get(0).promise.title);
        assertEquals(60, results.get(0).restMinutes);
        assertEquals("59m-after", results.get(1).promise.title);
        assertEquals(59, results.get(1).restMinutes);
        assertEquals("ontime", results.get(2).promise.title);
        assertEquals(0, results.get(2).restMinutes);
    }

    @Test
    public void testDifferentZone() {
        reminder.addPromise("1h-minus-on", ZonedDateTime.of(LocalDateTime.of(2014, 12, 25, 10, 0), ZoneId.of("UTC+8")));
        reminder.addPromise("1h-minus-off", ZonedDateTime.of(LocalDateTime.of(2014, 12, 25, 10, 1), ZoneId.of("UTC+8")));
        reminder.addPromise("1h-plus-on", ZonedDateTime.of(LocalDateTime.of(2014, 12, 25, 12, 0), ZoneId.of("UTC+10")));
        reminder.addPromise("1h-plus-off", ZonedDateTime.of(LocalDateTime.of(2014, 12, 25, 12, 1), ZoneId.of("UTC+10")));
        List<Notification> results = reminder.remind(ZonedDateTime.of(2014, 12, 25, 10, 0, 0, 0, ZoneId.of("UTC+9")));

        assertEquals(2, results.size());
        assertEquals("1h-minus-on", results.get(0).promise.title);
        assertEquals(60, results.get(0).restMinutes);
        assertEquals("1h-plus-on", results.get(1).promise.title);
        assertEquals(60, results.get(1).restMinutes);
    }
}