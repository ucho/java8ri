package ch03.ex11;

import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@FunctionalInterface
interface ColorTransformer {
    Color apply(int x, int y, Color colorAtXY);

    static ColorTransformer combine(ColorTransformer c1, ColorTransformer c2) {
        return (x, y, c) -> c2.apply(x, y, c1.apply(x, y, c));
    }

    static ColorTransformer create(UnaryOperator<Color> u) {
        return (x, y, c) -> u.apply(c);
    }
}

public class ImageTransformer extends Application {
    public static Image transform(Image in, ColorTransformer f) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y, f.apply(x, y, in.getPixelReader().getColor(x, y)));
            }
        }
        return out;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Image image = new Image("file:queen-mary.png");
        Image transformed = transform(image, ColorTransformer.combine((x, y, c) -> {
            if (x < 10 || x > image.getWidth() - 10 || y < 10 || y > image.getHeight() - 10) {
                return Color.GRAY;
            }
            return c;
        }, ColorTransformer.create(c -> c.brighter())));
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(transformed))));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}