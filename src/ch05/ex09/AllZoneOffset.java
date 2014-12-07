package ch05.ex09;

import static java.util.stream.Collectors.toMap;
import static org.junit.Assert.assertTrue;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.function.Predicate;

import org.junit.Test;

public class AllZoneOffset {

    public static Map<String, ZoneOffset> getAllOffsetWithOffsetFilter(Predicate<ZoneOffset> filter) {
        return ZoneId.getAvailableZoneIds().stream()
                .map(id -> ZonedDateTime.now(ZoneId.of(id)))
                .filter(time -> filter.test(time.getOffset()))
                .collect(toMap(time -> time.getZone().getId(), time -> time.getOffset()));
    }

    @Test
    public void testGetAllOffset1HBelow() {
        getAllOffsetWithOffsetFilter(offset -> Math.abs(offset.getTotalSeconds()) <= 3600)
                .forEach((id, offset) -> {
                    assertTrue(Math.abs(offset.getTotalSeconds()) <= 3600);
                    System.out.println(id + ": " + offset);
                });
    }
}
