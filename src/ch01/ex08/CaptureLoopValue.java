package ch01.ex08;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CaptureLoopValue {

    public static void main(String[] args) {
        String[] names = { "Peter", "Paul", "Mary" };
        List<Runnable> runners = new ArrayList<>();
        for (String name : names) {
            // ループ中のそれぞれの値は正しくキャプチャされる。
            // 正当なコードと言える。
            // 従来のfor文ではキャプチャできない(コンパイルエラーになる)。
            // 従来のfor文ではループ変数や、ループ変数で配列をインデックスした値が明らかにfinalではないため。
            // 拡張for文ではループごとに変数が宣言されるような扱いになっている？ためキャプチャできる。
            runners.add(() -> System.out.println(name));
        }

        ExecutorService executor = Executors.newFixedThreadPool(runners.size());
        for (Runnable r : runners) {
            executor.execute(r);
        }
        executor.shutdown();
    }

}
