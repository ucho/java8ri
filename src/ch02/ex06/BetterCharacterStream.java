package ch02.ex06;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BetterCharacterStream {

    public static Stream<Character> characterStream(String s) {
        return IntStream.range(0, s.length()).mapToObj(s::charAt);
    }

    public static void main(String[] args) {
        characterStream("boat").forEach(System.out::println);
    }

}
