package ch08.ex13;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.LongStream;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

@Repeatable(TestCases.class)
@Retention(RetentionPolicy.SOURCE)
@interface TestCase {
    int params();

    long expected();
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
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

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("ch08.ex13.TestCases")
public class TestCaseAnnotationProcessor extends AbstractProcessor {

    private static final String GENERATED_CLASS_NAME = "TestCaseGenerated";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        AtomicBoolean result = new AtomicBoolean(true);
        annotations.forEach(a -> {
            roundEnv.getElementsAnnotatedWith(a).forEach(e -> {
                TestCases cases = e.getAnnotation(TestCases.class);
                if (cases == null) {
                    result.set(false);
                }
                try {
                    writeSource(cases);
                } catch (IOException ex) {
                    System.err.println("failed to write file: " + ex.getMessage());
                }
            });
        });
        return result.get();
    }

    private void writeSource(TestCases cases) throws IOException {
        JavaFileObject source = processingEnv.getFiler().createSourceFile(GENERATED_CLASS_NAME);
        try (PrintWriter out = new PrintWriter(source.openWriter())) {
            out.println("package " + this.getClass().getPackage().getName() + ";");
            out.println("");
            out.println("public class " + GENERATED_CLASS_NAME + " {");
            out.println("  public static void main(String[] args) {");
            for (TestCase t : cases.value()) {
                out.println("    System.out.println(\"params = " + t.params() + ", "
                        + "expected = " + t.expected() + ", "
                        + "actual = \" + TestCaseExample.factorial(" + t.params() + "));");
            }
            out.println("  }");
            out.println("}");
        }
    }
}