package ch09.ex06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ReverseLines {

    public static void convert(Path from, Path to) throws IOException {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        List<String> lines = Files.readAllLines(from);
        Collections.reverse(lines);
        Files.write(to, lines, StandardOpenOption.CREATE);
    }

    public static void main(String[] args) throws Exception {
        convert(Paths.get("alice.txt"), Paths.get("/tmp/9-6.txt"));
    }
}
