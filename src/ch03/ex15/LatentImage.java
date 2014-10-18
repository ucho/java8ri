package ch03.ex15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class LatentImage {
    private Image in;

    private List<UnaryOperator<Color>> pendingOperations;

    private LatentImage(Image in) {
        this.in = in;
        this.pendingOperations = new ArrayList<>();
    }

    public static LatentImage from(Image image) {
        return new LatentImage(image);
    }

    public LatentImage transform(UnaryOperator<Color> f) {
        pendingOperations.add(f);
        return this;
    }

    public Image toImageInParallel() throws InterruptedException {
        Color[][] pixels = convertToPixels(in);
        Color[][] processed = transformInParallel(pixels);
        return convertToImage(processed);
    }

    private Color[][] convertToPixels(Image image) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();

        Color[][] pixels = new Color[height][width];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[y][x] = in.getPixelReader().getColor(x, y);
            }
        }

        return pixels;
    }

    private Color[][] transformInParallel(Color[][] in) throws InterruptedException {
        int width = in[0].length;
        int height = in.length;

        Color[][] out = new Color[height][width];
        int n = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(n);
        for (int i = 0; i < n; i++) {
            int fromY = i * height / n;
            int toY = (i + 1) * height / n;
            pool.submit(transformer(out, in, width, fromY, toY));
        }
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.HOURS);

        return out;
    }

    private Runnable transformer(Color[][] out, Color[][] in, int width, int fromY, int toY) {
        return () -> {
            System.out.printf("%s %d...%d\n", Thread.currentThread(), fromY, toY - 1);
            for (int x = 0; x < width; x++) {
                for (int y = fromY; y < toY; y++) {
                    for (UnaryOperator<Color> f : pendingOperations) {
                        out[y][x] = f.apply(in[y][x]);
                    }
                }
            }
        };
    }

    private Image convertToImage(Color[][] pixels) {
        int width = pixels[0].length;
        int height = pixels.length;

        WritableImage image = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.getPixelWriter().setColor(x, y, pixels[y][x]);
            }
        }
        return image;
    }
}
