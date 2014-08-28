package ch02.ex03;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class MeasureNormalAndParallel {

    public static void main(String[] args) throws Exception {
        String contents = new String(
                Files.readAllBytes(Paths.get("alice.txt")),
                StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        long startNormal = System.nanoTime();
        words.stream().filter(w -> w.length() > 12).count();
        long endNormal = System.nanoTime();
        System.out.println("normal:   " + (endNormal - startNormal));

        long startParallel = System.nanoTime();
        words.parallelStream().filter(w -> w.length() > 12).count();
        long endParallel = System.nanoTime();
        System.out.println("parallel: " + (endParallel - startParallel));
    }

}
