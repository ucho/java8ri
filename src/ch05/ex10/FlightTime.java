package ch05.ex10;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

public class FlightTime {

    public static ZonedDateTime calculateArrival(LocalDateTime departure, Duration flightTime,
            ZoneId originZone, ZoneId destinationZone) {
        return ZonedDateTime.of(departure.plus(flightTime), originZone).withZoneSameInstant(destinationZone);
    }

    @Test
    public void fromLosAngelsToFrankfurt() {
        ZonedDateTime expect = ZonedDateTime.of(2014, 12, 25, 22, 55, 0, 0, ZoneId.of("CET"));
        ZonedDateTime actual = calculateArrival(LocalDateTime.of(2014, 12, 25, 3, 5),
                Duration.ofHours(10).plusMinutes(50),
                ZoneId.of("America/Los_Angeles"), ZoneId.of("CET"));
        assertEquals(expect, actual);
    }
}
