package ch01.ex02;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AllSubDirectories {

    public static List<File> listSubDirectoriesLambda(File target) {
        List<File> result = new ArrayList<>();
        if (!target.isDirectory()) {
            return result;
        }
        File[] subdirs = target.listFiles(file -> {
            return file.isDirectory();
        });
        for (File s : subdirs) {
            result.add(s);
            result.addAll(listSubDirectoriesLambda(s));
        }
        return result;
    }

    public static List<File> listSubDirectoriesMethodReference(File target) {
        List<File> result = new ArrayList<>();
        if (!target.isDirectory()) {
            return result;
        }
        File[] subdirs = target.listFiles(File::isDirectory);
        for (File s : subdirs) {
            result.add(s);
            result.addAll(listSubDirectoriesMethodReference(s));
        }
        return result;
    }

    public static void main(String[] args) {
        String target = System.getProperty("user.dir");
        System.out.println("target: " + target);

        System.out.println("### lambda ###");
        for (File dir : listSubDirectoriesLambda(new File(target))) {
            System.out.println(dir);
        }
        System.out.println();

        System.out.println("### method reference ###");
        for (File dir : listSubDirectoriesMethodReference(new File(target))) {
            System.out.println(dir);
        }
    }
}
