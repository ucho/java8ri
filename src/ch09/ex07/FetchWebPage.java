package ch09.ex07;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class FetchWebPage {

    public static void fetch(URL from, Path to) throws IOException {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        Files.copy(from.openStream(), to, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void main(String[] args) throws Exception {
        fetch(new URL("http://www.horstmann.com/java8/index.html"), Paths.get("/tmp/9-7.txt"));
    }
}
