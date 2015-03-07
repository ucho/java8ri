package ch08.ex02;

import org.junit.Test;

public class NegateExactThrow {

    @Test(expected = ArithmeticException.class)
    public void test() {
        Math.negateExact(Integer.MIN_VALUE);
    }
}
