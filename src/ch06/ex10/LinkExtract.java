package ch06.ex10;

import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

// getを使わずに書けなかった。
// 本の説明には「計算は暗黙に開始されます」とあるが、
// 実際にはトリガがかかることによって計算が開始される。
// getは最も簡単にトリガをかける手段なので、ここではそれを使った。
// 本の説明にあるような、thenXXXメソッドではトリガはかからないようである。
public class LinkExtract {

    public static CompletableFuture<String> readPage(URL url) {
        Objects.requireNonNull(url);
        return CompletableFuture.supplyAsync(() -> {
            StringBuilder builder = new StringBuilder();
            try (Scanner in = new Scanner(url.openStream())) {
                while (in.hasNextLine()) {
                    builder.append(in.nextLine());
                    builder.append("\n");
                }
            } catch (IOException e) {
                return e.toString();
            }
            return builder.toString();
        });
    }

    public static CompletableFuture<List<String>> getLinks(String page) {
        Objects.requireNonNull(page);
        return CompletableFuture.supplyAsync(() -> {
            List<String> result = new ArrayList<>();
            Pattern pattern = Pattern.compile( // copy from sample code
                    "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>",
                    Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(page);
            while (matcher.find()) {
                result.add(matcher.group(1));
            }
            return result;
        });
    }

    @Test
    public void testGetLinksOfAPage() throws Exception {
        List<String> result = readPage(new URL("http://www.horstmann.com/java8/index.html"))
                .thenCompose(LinkExtract::getLinks)
                .get();
        assertFalse(result.isEmpty());
        result.stream().forEach(System.out::println);
    }
}
