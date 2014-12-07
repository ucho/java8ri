package ch05.ex11;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

public class FlightTime {

    public static Duration calculateFlightTime(ZonedDateTime departure, ZonedDateTime arrival) {
        return Duration.between(
                departure.withZoneSameInstant(arrival.getZone()).toLocalDateTime(),
                arrival.toLocalDateTime());
    }

    @Test
    public void fromFrankfurtToLosAngels() {
        Duration expect = Duration.ofHours(11).plusMinutes(35);
        Duration actual = calculateFlightTime(
                ZonedDateTime.of(2014, 12, 25, 14, 5, 0, 0, ZoneId.of("CET")),
                ZonedDateTime.of(2014, 12, 25, 16, 40, 0, 0, ZoneId.of("America/Los_Angeles")));
        assertEquals(expect, actual);
    }
}
