package ch03.ex03;

import static org.junit.Assert.assertEquals;

import java.util.function.BooleanSupplier;

import org.junit.Test;

// なぜアサーションはライブラリの機能として提供されなかったのか？
//   -> 条件式を直接渡すような書き方ができなかったから
// Java8でライブラリの機能として実装できるか？
//   -> 下記のように、ラムダ式を使えば似たようなことはできる
public class ReproduceAssertion {

    public static <T> void assertIt(BooleanSupplier cond) {
        if (!cond.getAsBoolean()) {
            throw new AssertionError();
        }
    }

    public static <T> void assertIt(BooleanSupplier cond, String msg) {
        if (!cond.getAsBoolean()) {
            throw new AssertionError(msg);
        }
    }

    // AssertionErrorをcatchすると、fail()によるAssertionErrorもcatchしてしまい、
    // テストができなくなるため、このようなメソッドが必要になる。
    private static void myFail() {
        throw new RuntimeException();
    }

    @Test
    public void testAssertIt() {
        try {
            assertIt(() -> 1 > 2);
            myFail();
        } catch (AssertionError e) {
        }
    }

    @Test
    public void testAssertItWithMessage() {
        try {
            assertIt(() -> 1 > 2, "assertion error!");
            myFail();
        } catch (AssertionError e) {
            assertEquals("assertion error!", e.getMessage());
        }
    }
}
