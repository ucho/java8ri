package ch08.ex11;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.stream.LongStream;

import javassist.ClassPool;
import javassist.CtClass;

import org.junit.Test;

@Repeatable(TestCases.class)
@interface TestCase {
    int params();

    long expected();
}

@Target(ElementType.METHOD)
@interface TestCases {
    TestCase[] value();
}

class TestCaseExample {

    @TestCase(params = 0, expected = 1)
    @TestCase(params = 4, expected = 24)
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be greater than 0.");
        }
        return LongStream.rangeClosed(2, n).reduce(1, (a, b) -> a * b);
    }
}

public class ValidateTestCaseAnnotation {

    private static final String TARGET_CLASS_NAME = "ch08.ex11.TestCaseExample";

    @Test
    public void validateTestCases() throws Exception {
        CtClass cc = ClassPool.getDefault().getCtClass(TARGET_CLASS_NAME);
        Class<?> cls = Class.forName(TARGET_CLASS_NAME);
        Arrays.stream(cc.getDeclaredMethods()).forEach(method -> {
            Object[] annotations = null;
            try {
                annotations = method.getAnnotations();
            } catch (ClassNotFoundException e) {
                fail(e.getMessage());
            }
            Arrays.stream(annotations).forEach(annotation -> {
                if (annotation instanceof TestCases) {
                    return;
                }
                String name = method.getName();
                TestCases cases = (TestCases) annotation;
                Arrays.stream(cases.value()).forEach(testCase -> {
                    int params = testCase.params();
                    long expected = testCase.expected();
                    try {
                        Object result = cls.getMethod(name, int.class).invoke(null, params);
                        assertEquals(expected, result);
                    } catch (ReflectiveOperationException e) {
                        fail(e.getMessage());
                    }
                });
            });
        });
    }
}