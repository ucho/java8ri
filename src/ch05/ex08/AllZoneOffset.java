package ch05.ex08;

import static java.util.stream.Collectors.toMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.function.Function;

import org.junit.Test;

public class AllZoneOffset {

    public static Map<String, ZoneOffset> getAllOffset() {
        return ZoneId.getAvailableZoneIds().stream().collect(
                toMap(Function.identity(), id -> ZonedDateTime.now(ZoneId.of(id)).getOffset()));
    }

    @Test
    public void testGetAllOffset() {
        Map<String, ZoneOffset> result = getAllOffset();
        assertEquals(ZoneId.getAvailableZoneIds().size(), result.keySet().size());
        result.forEach((id, offset) -> {
            assertNotNull(offset);
            System.out.println(id + ": " + offset);
        });
    }
}
