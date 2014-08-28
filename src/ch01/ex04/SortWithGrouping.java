package ch01.ex04;

import java.io.File;
import java.util.Arrays;

public class SortWithGrouping {

    public static void sortWithGrouping(File[] target) {
        Arrays.sort(target, (first, second) -> {
            boolean fisdir = first.isDirectory();
            boolean sisdir = second.isDirectory();

            if (fisdir == sisdir) {
                return first.compareTo(second);
            } else {
                return fisdir ? -1 : 1;
            }
        });
    }

    public static void main(String[] args) {
        File[] target = new File(System.getProperty("user.dir")).listFiles();
        sortWithGrouping(target);
        Arrays.stream(target).forEach(System.out::println);
    }
}
