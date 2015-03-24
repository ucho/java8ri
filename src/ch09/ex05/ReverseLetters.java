package ch09.ex05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public class ReverseLetters {

    public static void convert(Path from, Path to) throws IOException {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        byte[] bytes = Files.readAllBytes(from);
        byte[] reversedBytes = new byte[bytes.length];
        int pos = bytes.length - 1;
        for (byte b : bytes) {
            reversedBytes[pos--] = b;
        }
        Files.write(to, reversedBytes, StandardOpenOption.CREATE);
    }

    public static void main(String[] args) throws Exception {
        convert(Paths.get("alice.txt"), Paths.get("/tmp/9-5.txt"));
    }
}
