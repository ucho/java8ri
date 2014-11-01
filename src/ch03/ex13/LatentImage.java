package ch03.ex13;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

interface ColorTransformer {
    Color apply(int x, int y, Color c, Color[][] pixels9);
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
        return transform((x, y, c, ps) -> f.apply(c));
    }

    public LatentImage transform(ColorTransformer f) {
        pendingOperations.add(f);
        return this;
    }

    public Image toImage() {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color c = in.getPixelReader().getColor(x, y);
                Color[][] pixels9 = new Color[3][3];
                for (int kx = 0; kx < 3; kx++) {
                    for (int ky = 0; ky < 3; ky++) {
                        int tx = fixEdgePixel(x + kx - 1, x);
                        int ty = fixEdgePixel(y + ky - 1, y);
                        pixels9[kx][ky] = in.getPixelReader().getColor(tx, ty);
                    }
                }
                for (ColorTransformer f : pendingOperations) {
                    c = f.apply(x, y, c, pixels9);
                }
                out.getPixelWriter().setColor(x, y, c);
            }
        }
        return out;
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
}
