package ch06.ex07;

import static org.junit.Assert.assertEquals;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class FindAtConcurrentHashMap {

    public static String findKeyOfMaxValue(ConcurrentHashMap<String, Long> map) {
        Objects.requireNonNull(map);
        return map.reduceEntries(1, (e1, e2) -> e1.getValue() > e2.getValue() ? e1 : e2).getKey();
    }

    @Test
    public void testFindKeyOfMaxValue() {
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        map.put("2014.8", 3453L);
        map.put("2014.9", 3845L);
        map.put("2014.10", 3931L);
        map.put("2014.11", 3110L);
        map.put("2014.12", 3058L);

        assertEquals("2014.10", findKeyOfMaxValue(map));
    }
}
