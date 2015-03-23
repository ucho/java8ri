package ch09.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class NonTryWithResource {

    public static void main(String[] args) {
        Exception inCloseException = null;
        Exception outCloseException = null;
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
                    try {
                        in.close();
                    } catch (IllegalStateException e) {
                        inCloseException = e;
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (Exception e) {
                        outCloseException = e;
                    }
                }
            }
        } catch (IOException e) {
            if (inCloseException != null) {
                e.addSuppressed(inCloseException);
            }
            if (outCloseException != null) {
                e.addSuppressed(outCloseException);
            }
            // catch constructors, in.close(), out.close()
            System.err.println(e);
            Arrays.stream(e.getSuppressed()).forEach(System.err::println);
        }
    }

}
