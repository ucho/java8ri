package ch02.ex02;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ValidateNotCalledFilter {

    public static void main(String[] args) throws Exception {
        String contents = new String(
                Files.readAllBytes(Paths.get("alice.txt")),
                StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        long count = words.stream().filter(w -> w.length() > 12)
                .peek(System.out::println).limit(5).count();
        System.out.println(count);
    }

}
