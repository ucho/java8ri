package ch09.ex11;

import java.nio.file.Paths;

public class CollectCardNumbers {

    public static void main(String[] args) throws Exception {
        ProcessBuilder builder = new ProcessBuilder("grep", "-r", "[0-9]\\{14,16\\}", ".");
        builder.directory(Paths.get(System.getProperty("user.home")).toFile());
        builder.redirectOutput(Paths.get("/tmp/9-11.txt").toFile());
        builder.start().waitFor();
    }
}
