package ch02.ex13;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class WordCountParallelStream {

    public static int[] countShortWordsParallel(Stream<String> words, int thresh) {
        Map<Integer, Long> counts = words.parallel()
                .filter(s -> s.length() < thresh)
                .collect(groupingBy(s -> s.length(), counting()));
        int[] result = new int[thresh];
        for (Integer c : counts.keySet()) {
            result[c] = counts.get(c).intValue();
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
