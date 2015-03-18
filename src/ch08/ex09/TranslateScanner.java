package ch08.ex09;

import static org.junit.Assert.assertArrayEquals;

import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.junit.Test;

public class TranslateScanner {

    public static Stream<String> lines(Scanner scanner) {
        Objects.requireNonNull(scanner);

        Iterator<String> iter = new Iterator<String>() {

            @Override
            public boolean hasNext() {
                return scanner.hasNextLine();
            }

            @Override
            public String next() {
                return scanner.nextLine();
            }
        };

        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    @Test
    public void testLines() {
        String input = "a\nb\nc";
        Scanner scanner = new Scanner(input);
        assertArrayEquals(input.split("\n"), lines(scanner).toArray());
    }

    public static Stream<String> words(Scanner scanner) {
        Objects.requireNonNull(scanner);

        final String pattern = "\\w+";

        Iterator<String> iter = new Iterator<String>() {

            @Override
            public boolean hasNext() {
                return scanner.hasNext(pattern);
            }

            @Override
            public String next() {
                return scanner.next(pattern);
            }
        };

        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    @Test
    public void testWords() {
        String input = "This is a pen";
        Scanner scanner = new Scanner(input);
        assertArrayEquals(input.split("[\\P{L}]+"), words(scanner).toArray());
    }

    public static Stream<Integer> integers(Scanner scanner) {
        Objects.requireNonNull(scanner);

        Iterator<Integer> iter = new Iterator<Integer>() {

            @Override
            public boolean hasNext() {
                return scanner.hasNextInt();
            }

            @Override
            public Integer next() {
                return scanner.nextInt();
            }
        };

        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    @Test
    public void testIntegers() {
        String input = "1 2 3 4 5";
        Integer[] expected = new Integer[] { 1, 2, 3, 4, 5 };
        Scanner scanner = new Scanner(input);
        assertArrayEquals(expected, integers(scanner).toArray());
    }

    public static Stream<Double> doubles(Scanner scanner) {
        Objects.requireNonNull(scanner);

        Iterator<Double> iter = new Iterator<Double>() {

            @Override
            public boolean hasNext() {
                return scanner.hasNextDouble();
            }

            @Override
            public Double next() {
                return scanner.nextDouble();
            }
        };

        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    @Test
    public void testDoubles() {
        String input = "1.1 2.2 3.3 4.4 5.5";
        Double[] expected = new Double[] { 1.1, 2.2, 3.3, 4.4, 5.5 };
        Scanner scanner = new Scanner(input);
        assertArrayEquals(expected, doubles(scanner).toArray());
    }
}
