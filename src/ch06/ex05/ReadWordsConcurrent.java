package ch06.ex05;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.junit.Test;

public class ReadWordsConcurrent {

    private Set<File> files;
    private Map<String, Set<File>> result;

    public ReadWordsConcurrent() {
        files = new HashSet<>();
        result = new ConcurrentHashMap<>();
    }

    public void addFiles(File... files) {
        Objects.requireNonNull(files);
        Arrays.stream(files).forEach(this.files::add);
    }

    public Set<File> files() {
        return files;
    }

    public void read() throws InterruptedException {
        ExecutorService ex = Executors.newCachedThreadPool();
        files.stream().forEach(file -> {
            ex.submit(createTask(file));
        });
        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.MINUTES);
    }

    private Runnable createTask(File file) {
        return () -> {
            try (Stream<String> lines = Files.lines(file.toPath())) {
                lines.flatMap(line -> Arrays.stream(line.split("[\\P{L}]+"))).forEach(word -> {
                    Set<File> initValue = new HashSet<>();
                    initValue.add(file);
                    result.merge(word, initValue, (existingValue, newValue) -> {
                        existingValue.addAll(newValue);
                        return existingValue;
                    });
                });
            } catch (IOException e) {
                System.err.println(e);
            }
        };
    }

    public Set<File> getFilesByWord(String word) {
        return result.get(word);
    }

    @Test
    public void testAddFiles() {
        File file01 = Paths.get("alice.txt").toFile();
        File file02 = Paths.get("war_and_peace.txt").toFile();

        ReadWordsConcurrent app = new ReadWordsConcurrent();

        assertTrue(app.files.isEmpty());

        app.addFiles(file01);
        assertEquals(1, app.files.size());
        assertTrue(app.files.contains(file01));

        app.addFiles(file02);
        assertEquals(2, app.files.size());
        assertTrue(app.files.contains(file01));
        assertTrue(app.files.contains(file02));
    }

    @Test
    public void testGetFilesByWord() throws Exception {
        File file01 = Paths.get("alice.txt").toFile();
        File file02 = Paths.get("war_and_peace.txt").toFile();

        ReadWordsConcurrent app = new ReadWordsConcurrent();
        app.addFiles(file01, file02);

        assertNull(app.getFilesByWord("CHAPTER"));

        app.read();

        app.result.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });

        Set<File> resultBoth = app.getFilesByWord("CHAPTER");
        assertEquals(2, resultBoth.size());
        assertTrue(resultBoth.contains(file01));
        assertTrue(resultBoth.contains(file02));

        Set<File> resultOne = app.getFilesByWord("Alice");
        assertEquals(1, resultOne.size());
        assertTrue(resultOne.contains(file01));
    }
}
