package ch04.ex05;

import static org.junit.Assert.assertEquals;

import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import org.junit.Test;

public class ObserveUtil {

    public static <T, R> ObservableValue<R> observe(Function<T, R> f, ObservableValue<T> t) {
        return new ObjectBinding<R>() {
            {
                bind(t);
            }

            @Override
            protected R computeValue() {
                return f.apply(t.getValue());
            }
        };
    }

    public static <T, U, R> ObservableValue<R> observe(BiFunction<T, U, R> f, ObservableValue<T> t, ObservableValue<U> u) {
        return new ObjectBinding<R>() {
            {
                bind(t, u);
            }

            @Override
            public R computeValue() {
                return f.apply(t.getValue(), u.getValue());
            }
        };
    }

    @Test
    public void testObserveUnary() {
        StringProperty text = new SimpleStringProperty("");
        ObservableValue<Boolean> target = observe(t -> t.length() > 6, text);
        int[] result = new int[2];
        target.addListener((property, oldValue, newValue) -> {
            result[0]++;
        });
        target.addListener(observable -> {
            result[1]++;
        });

        text.set("hoge"); // invalidated
        text.set("hogehoge"); // changed, invalidated
        text.set("hogehog"); // invalidated
        text.set("hogeho"); // changed, invalidated

        assertEquals(2, result[0]);
        assertEquals(4, result[1]);
    }

    @Test
    public void testObserveBinary() {
        StringProperty text1 = new SimpleStringProperty("");
        StringProperty text2 = new SimpleStringProperty("");
        ObservableValue<Boolean> target = observe((t1, t2) -> t1.equals(t2), text1, text2);
        int[] result = new int[2];
        target.addListener((property, oldValue, newValue) -> {
            result[0]++;
        });
        target.addListener(observable -> {
            result[1]++;
        });

        text1.set("I love you."); // changed, invalidated
        text2.set("I like you."); // invalidated
        text2.set("I love you."); // changed, invalidated
        text2.set("I love you, too."); // changed, invalidated

        assertEquals(3, result[0]);
        assertEquals(4, result[1]);
    }

}
