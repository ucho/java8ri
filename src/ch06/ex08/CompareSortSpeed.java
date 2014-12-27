package ch06.ex08;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.junit.Test;

// Math.random()で埋めた配列を、サイズ1000から1000ずつ増やして
// Arrays.sortとArrays.parallelSortの実行時間を比較する。
// parallelSortの方が速い結果が出たら、そこから同一サイズで繰り返し3回比較を行い、
// 全ての比較でparallelSortの方が速ければ結果を表示して終了する。
//
// 内山のPCにおいて、このコードではparallelSortの方が安定して速いサイズが求められなかった。
// 10000回前後が多い印象だが、800000回になることもあった。
public class CompareSortSpeed {

    private static final int INITIAL_SIZE = 1000;
    private static final int STEP_SIZE = 1000;
    private static final int CONFIRM_COUNT = 3;

    @Test
    public void testCompareSort() {
        for (int size = INITIAL_SIZE;; size += STEP_SIZE) {
            Duration resultParallel = measureParallelSort(generateRandomArray(size));
            Duration resultNormal = measureSort(generateRandomArray(size));
            if (resultParallel.compareTo(resultNormal) < 0) {
                if (confirm(size, CONFIRM_COUNT)) {
                    System.out.println("size: " + size);
                    System.out.println("paralell: " + resultParallel.toMillis() + "ms");
                    System.out.println("normal: " + resultNormal.toMillis() + "ms");
                    return;
                }
            }
        }
    }

    private boolean confirm(int size, int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            Duration resultParallel = measureParallelSort(generateRandomArray(size));
            Duration resultNormal = measureSort(generateRandomArray(size));
            if (resultParallel.compareTo(resultNormal) < 0) {
                count++;
            } else {
                break;
            }
        }
        return count == n;
    }

    private double[] generateRandomArray(int size) {
        double[] array = new double[size];
        Arrays.fill(array, Math.random());
        return array;
    }

    private Duration measureParallelSort(double[] array) {
        Instant before = Instant.now();
        Arrays.parallelSort(array);
        Instant after = Instant.now();
        return Duration.between(before, after);
    }

    private Duration measureSort(double[] array) {
        Instant before = Instant.now();
        Arrays.sort(array);
        Instant after = Instant.now();
        return Duration.between(before, after);
    }
}
