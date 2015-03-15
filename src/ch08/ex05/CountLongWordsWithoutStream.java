package ch08.ex05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CountLongWordsWithoutStream {

    public static List<String> getWords() throws IOException {
        String contents = new String(
                Files.readAllBytes(Paths.get("alice.txt")),
                StandardCharsets.UTF_8);
        return Arrays.asList(contents.split("[\\P{L}]+"));
    }

    @Test
    public void countWithStream() throws Exception {
        List<String> words = getWords();
        long start = System.nanoTime();
        long count = words.stream().filter(w -> w.length() > 12).count();
        long end = System.nanoTime();
        System.out.println("with stream: time: " + (end - start) + ", count: " + count);
    }

    @Test
    public void countWithoutStream() throws Exception {
        List<String> words = getWords();
        long start = System.nanoTime();
        List<String> filtered = new ArrayList<>();
        words.forEach(w -> {
            if (w.length() > 12) {
                filtered.add(w);
            }
        });
        long count = filtered.size();
        long end = System.nanoTime();
        System.out.println(" w/o stream: time: " + (end - start) + ", count: " + count);
    }
}
