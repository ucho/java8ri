package ch02.ex01;

import static org.junit.Assert.assertEquals;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class WordCount {

    public static int countLongWords(List<String> words, int length) {
        int count = 0;
        for (String w : words) {
            if (w.length() > length) {
                count++;
            }
        }
        return count;
    }

    public static int countLongWordsParallel(List<String> words, int length,
            int nthreads) {
        int segSize = words.size() / nthreads;

        Runnable[] workers = new Runnable[nthreads];
        int[] counts = new int[nthreads];

        for (int i = 0; i < nthreads; i++) {
            int seg = i;
            workers[seg] = () -> {
                int offset = seg * segSize;
                for (int p = offset; p < offset + segSize; p++) {
                    if (words.get(p).length() > length) {
                        counts[seg]++;
                    }
                }
            };
        }

        Arrays.stream(workers).forEach(w -> {
            new Thread(w).start();
        });

        return Arrays.stream(counts).sum();
    }

    @Test
    public void testCountParallel() throws Exception {
        String contents = new String(
                Files.readAllBytes(Paths.get("alice.txt")),
                StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        assertEquals(countLongWords(words, 12),
                countLongWordsParallel(words, 12, 4));
    }

}
