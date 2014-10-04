package ch03.ex09;

import java.lang.reflect.Field;
import java.util.Comparator;

public class ComparatorUtil {

    public static Comparator<Object> lexicographicComparator(String... fieldNames) {
        return (a, b) -> {
            for (String fieldName : fieldNames) {
                try {
                    Field fa = a.getClass().getDeclaredField(fieldName);
                    Field fb = b.getClass().getDeclaredField(fieldName);
                    Object va = fa.get(a);
                    Object vb = fb.get(b);
                    if (!va.equals(vb)) {
                        return va.toString().compareTo(vb.toString());
                    }
                } catch (IllegalArgumentException | SecurityException | ReflectiveOperationException e) {
                    throw new IllegalArgumentException(e);
                }
            }
            return 0;
        };
    }

}