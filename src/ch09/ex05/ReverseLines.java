package ch09.ex05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

public class ReverseLines {

    public static Stream<String> reverse(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        Collections.reverse(lines);
        return lines.stream();
    }

    @Test
    public void example() throws Exception {
        reverse(Paths.get("alice.txt")).forEach(System.out::println);
    }
}
