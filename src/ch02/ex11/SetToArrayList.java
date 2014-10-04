package ch02.ex11;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.junit.Test;

public class SetToArrayList {

    // 結局Streamの大きさを先に知っておく方法が見つけられなかった
    // spliteratorを使えば大きさを知ることはできるが、終端操作のためStreamが使えなくなってしまう
    // ところでArrayListに並行な操作が発生するとConcurrentModificationExceptionが発生するので、
    // それを回避するためにはロックする必要があるのだが、
    // それだと結局parallel()の意味ないんじゃないか？
    public static <E> ArrayList<E> toArrayList(Stream<E> target) {
        ArrayList<E> result = new ArrayList<>(1);
        AtomicInteger index = new AtomicInteger();
        target.parallel().forEach((e) -> {
            synchronized (result) {
                int i = index.getAndIncrement();
                result.ensureCapacity(i);
                result.add(i, e); // そのindexにaddしてないとsetは使えないのでadd
                System.out.println(result);
            }
        });
        return result;
    }

    @Test
    public void testToArrayList() {
        Integer[] items = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        Stream<Integer> target = Arrays.stream(items);
        ArrayList<Integer> result = toArrayList(target);
        assertTrue(new HashSet<>(result).containsAll(Arrays.asList(items)));
    }
}
