package ch09.ex05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

public class ReverseLetters {

    public static Stream<Character> reverse(Path path) throws IOException {
        byte[] bytes = Files.readAllBytes(path);
        List<Character> charList = new ArrayList<>();
        for (byte b : bytes) {
            charList.add((char) b);
        }
        Collections.reverse(charList);
        return charList.stream();
    }

    @Test
    public void example() throws Exception {
        reverse(Paths.get("alice.txt")).forEach(System.out::print);
    }

}
