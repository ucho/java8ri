package ch08.ex10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class FindKeywordsFromSrc {

    public static List<Path> retrieveFilesWith(Path dir, Predicate<String> filter) throws IOException {
        Objects.requireNonNull(dir);
        Objects.requireNonNull(filter);
        try (Stream<Path> entries = Files.walk(dir)) {
            return entries.filter(path -> {
                if (path.toFile().isDirectory()) {
                    return false;
                }
                try (Stream<String> lines = Files.lines(path)) {
                    return lines.anyMatch(filter);
                } catch (IOException e) {
                    System.err.println(e);
                }
                return false;
            }).collect(Collectors.toList());
        }
    }

    @Test
    public void printFilesContainsKeywords() throws Exception {
        retrieveFilesWith(Paths.get("/tmp/src/"), line -> {
            return line.contains("transient") || line.contains("volatile");
        }).forEach(System.out::println);
    }
}
