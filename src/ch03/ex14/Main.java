package ch03.ex14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Image image = new Image("file:eiffel-tower.jpg");
        Image image1 = LatentImage.from(image).transform(new EdgeDetectionTransformer()).toImage();
        Image image2 = LatentImage.from(image).transform(new BlurTransformer()).toImage();
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(image1), new ImageView(image2))));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class EdgeDetectionTransformer extends CombolutionTransformer {

    private final int[][] KERNEL = { { 0, 1, 0 }, { 1, -4, 1 }, { 0, 1, 0 } };

    @Override
    protected double kernel(int x, int y) {
        return KERNEL[x][y];
    }
}

class BlurTransformer extends CombolutionTransformer {

    private final int[][] KERNEL = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };

    @Override
    protected double kernel(int x, int y) {
        return KERNEL[x][y];
    }
}

abstract class CombolutionTransformer implements ColorTransformer {

    @Override
    public Color apply(int x, int y, PixelReader r) {
        double newRed = 0;
        double newGreen = 0;
        double newBlue = 0;
        for (int kx = 0; kx < 3; kx++) {
            for (int ky = 0; ky < 3; ky++) {
                Color before = r.getColor((x + kx - 1), (y + ky - 1));
                newRed += before.getRed() * kernel(kx, ky);
                newGreen += before.getGreen() * kernel(kx, ky);
                newBlue += before.getBlue() * kernel(kx, ky);
            }
        }
        newRed = fixColorValue(newRed);
        newGreen = fixColorValue(newGreen);
        newBlue = fixColorValue(newBlue);
        return new Color(newRed, newGreen, newBlue, 1);
    }

    protected abstract double kernel(int x, int y);

    private double fixColorValue(double value) {
        if (value < 0) {
            return 0;
        }
        if (value > 1) {
            return 1;
        }
        return value;

    }
}