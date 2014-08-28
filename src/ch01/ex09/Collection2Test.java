package ch01.ex09;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.junit.Test;

@SuppressWarnings("serial")
class ArrayList2<T> extends ArrayList<T> implements Collection2<T> {
}

public class Collection2Test {

    @Test
    public void testForEachIf() {
        Collection2<String> targets = new ArrayList2<>();
        targets.add("ex1_1");
        targets.add("ex1_3");
        targets.add("ex2_6");

        List<String> results = new ArrayList<>();

        // 文字列のListに対して、特定の条件を満たす文字列だけを
        // 加工して別のListにコピーしている。
        Consumer<String> action = target -> {
            results.add(target.concat("_done"));
        };
        Predicate<String> filter = target -> {
            return target.startsWith("ex1");
        };
        targets.forEachIf(action, filter);

        assertEquals(results.size(), 2);
        results.forEach(e -> {
            assertTrue(e.startsWith("ex1"));
            assertTrue(e.endsWith("_done"));
        });
    }

}
