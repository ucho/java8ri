package ch03.ex14;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;

interface ColorTransformer {
    Color apply(int x, int y, PixelReader r);
}

public class LatentImage {
    private Image in;

    private List<ColorTransformer> pendingOperations;

    private LatentImage(Image in) {
        this.in = in;
        this.pendingOperations = new ArrayList<>();
    }

    public static LatentImage from(Image image) {
        return new LatentImage(image);
    }

    public LatentImage transform(UnaryOperator<Color> f) {
        return transform((x, y, r) -> f.apply(r.getColor(x, y)));
    }

    public LatentImage transform(ColorTransformer f) {
        pendingOperations.add(f);
        return this;
    }

    public Image toImage() {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        Image base = in;
        for (ColorTransformer f : pendingOperations) {
            PixelReader r = new CachedPixelReader(base);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    Color c = f.apply(x, y, r);
                    out.getPixelWriter().setColor(x, y, c);
                }
            }
            base = out;
        }
        return out;
    }
}

class CachedPixelReader implements PixelReader {

    private PixelReader r;

    private Color[][] cache;

    public CachedPixelReader(Image in) {
        this.r = in.getPixelReader();
        this.cache = new Color[(int) in.getHeight()][(int) in.getWidth()];
    }

    @SuppressWarnings("rawtypes")
    @Override
    public PixelFormat getPixelFormat() {
        return r.getPixelFormat();
    }

    @Override
    public int getArgb(int x, int y) {
        return r.getArgb(x, y);
    }

    @Override
    public Color getColor(int x, int y) {
        int xx = fixEdgePixel(x, cache[0].length - 1);
        int yy = fixEdgePixel(y, cache.length - 1);
        if (cache[yy][xx] == null) {
            cache[yy][xx] = r.getColor(xx, yy);
        }
        return cache[yy][xx];
    }

    private int fixEdgePixel(int t, int max) {
        if (t < 0) {
            return 0;
        }
        if (t > max) {
            return max;
        }
        return t;
    }

    @Override
    public <T extends Buffer> void getPixels(int x, int y, int w, int h, WritablePixelFormat<T> pixelformat, T buffer,
            int scanlineStride) {
        r.getPixels(x, y, w, h, pixelformat, buffer, scanlineStride);
    }

    @Override
    public void getPixels(int x, int y, int w, int h, WritablePixelFormat<ByteBuffer> pixelformat, byte[] buffer,
            int offset, int scanlineStride) {
        r.getPixels(x, y, w, h, pixelformat, buffer, offset, scanlineStride);
    }

    @Override
    public void getPixels(int x, int y, int w, int h, WritablePixelFormat<IntBuffer> pixelformat, int[] buffer,
            int offset, int scanlineStride) {
        r.getPixels(x, y, w, h, pixelformat, buffer, offset, scanlineStride);
    }
}
