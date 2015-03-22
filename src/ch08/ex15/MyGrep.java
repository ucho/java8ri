package ch08.ex15;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

import org.junit.Test;

public class MyGrep {

    public static List<String> filterLines(Path path, String pattern) {
        if (path == null || pattern == null) {
            System.err.println("paramaters must not be null.");
            return Collections.emptyList();
        }
        try {
            Pattern p = Pattern.compile(pattern);
            return filterLines(path, p);
        } catch (IOException e) {
            System.err.println("failed to open file: " + path.getFileName());
            return Collections.emptyList();
        } catch (PatternSyntaxException e) {
            System.err.println("invalid pattern: " + pattern + " : " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private static List<String> filterLines(Path path, Pattern pattern) throws IOException {
        return Files.lines(path).filter(pattern.asPredicate()).collect(Collectors.toList());
    }

    @Test
    public void testFoundLines() {
        assertEquals(5, filterLines(Paths.get("alice.txt"), "^Queen").size());
    }

    @Test
    public void testNullParams() {
        assertTrue(filterLines(Paths.get("alice.txt"), (String) null).isEmpty());
        assertTrue(filterLines(null, (String) null).isEmpty());
        assertTrue(filterLines(null, "^Queen").isEmpty());
    }

    @Test
    public void testOpenFailed() {
        assertTrue(filterLines(Paths.get("aliceeee.txt"), "^Queen").isEmpty());
    }

    @Test
    public void testInvalidPattern() {
        assertTrue(filterLines(Paths.get("alice.txt"), "^[]^").isEmpty());
    }
}
