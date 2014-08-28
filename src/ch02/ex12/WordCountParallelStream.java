package ch02.ex12;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class WordCountParallelStream {

    public static int[] countShortWordsParallel(Stream<String> words, int thresh) {
        AtomicInteger[] shortWords = new AtomicInteger[thresh];
        words.parallel().forEach(s -> {
            int sLen = s.length();
            if (sLen < thresh) {
                if (shortWords[sLen] == null) {
                    shortWords[sLen] = new AtomicInteger();
                }
                shortWords[sLen].getAndIncrement();
            }
        });

        int[] result = new int[thresh];
        for (int i = 0; i < thresh; i++) {
            result[i] = shortWords[i].get();
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        String contents = new String(
                Files.readAllBytes(Paths.get("alice.txt")),
                StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        int[] shortWords = countShortWordsParallel(words.stream(), 12);
        System.out.println(Arrays.toString(shortWords));
    }
}
