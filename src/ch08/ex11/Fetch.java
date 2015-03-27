package ch08.ex11;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.Scanner;

public class Fetch {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("usage: " + Fetch.class + " url user pass");
            System.exit(1);
        }

        String credential = args[1] + ':' + args[2];
        String encoded = Base64.getEncoder().encodeToString(credential.getBytes());

        try {
            URL url = new URL(args[0]);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("Authorization", "Basic " + encoded);
            conn.connect();

            try (Scanner scanner = new Scanner(conn.getInputStream())) {
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
            }
        } catch (MalformedURLException e) {
            System.err.println("invalid url: " + args[0]);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("connection error:" + e.getMessage());
            System.exit(1);
        }
    }
}