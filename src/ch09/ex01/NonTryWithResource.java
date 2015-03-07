package ch09.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.Test;

public class NonTryWithResource {

    @Test
    public void test() {
        try {
            Scanner in = null;
            PrintWriter out = null;
            try {
                in = new Scanner(Paths.get("/usr/share/dict/words"));
                out = new PrintWriter("/tmp/out.txt");
                try {
                    while (in.hasNext()) {
                        out.println(in.next().toLowerCase());
                    }
                } catch (NullPointerException | IllegalStateException | NoSuchElementException e) {
                    // catch in.hasNext(), in.next()
                    System.err.println(e);
                }
            } finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }
        } catch (IOException e) {
            // catch constructors, in.close(), out.close()
            System.err.println(e);
        }
    }
}
