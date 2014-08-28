package ch02.ex04;

import java.util.Arrays;
import java.util.stream.Stream;

public class CreateIntStream {

    public static void main(String[] args) {
        int[] values = { 1, 4, 9, 16 };

        // Stream.of(int[]) だと int[] のストリームができてしまう
        // 以下のコードでは values のアドレスが表示されるだけ
        Stream.of(values).forEach(System.out::println);

        // Arrays.stream(int[]) を使えば int のストリームが得られる
        Arrays.stream(values).forEach(System.out::println);
    }

}
