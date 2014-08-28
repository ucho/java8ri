package ch01.ex03;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AllTypedFiles {

    public static List<File> listTypedFiles(File target, String suffix) {
        List<File> result = new ArrayList<>();
        if (!target.isDirectory()) {
            return result;
        }

        // suffixがエンクロージングスコープからキャプチャされる変数
        File[] typedFiles = target.listFiles((dir, name) -> {
            return name.endsWith(suffix);
        });
        for (File f : typedFiles) {
            result.add(f);
        }

        File[] subdirs = target.listFiles(File::isDirectory);
        for (File s : subdirs) {
            result.addAll(listTypedFiles(s, suffix));
        }
        return result;
    }

    public static void main(String[] args) {
        String target = System.getProperty("user.dir");
        String suffix = ".java";

        System.out.println("target: " + target);
        System.out.println("suffix: " + suffix);

        for (File f : listTypedFiles(new File(target), suffix)) {
            System.out.println(f);
        }
    }
}
