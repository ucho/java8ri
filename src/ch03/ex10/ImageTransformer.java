package ch03.ex10;

import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ImageTransformer extends Application {
    public static Image transform(Image in, UnaryOperator<Color> f) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y)));
            }
        }
        return out;
    }

    public static <T> UnaryOperator<T> compose(UnaryOperator<T> op1, UnaryOperator<T> op2) {
        return t -> op2.apply(op1.apply(t));
    }

    @Override
    public void start(Stage stage) throws Exception {
        Image image = new Image("file:queen-mary.png");
        UnaryOperator<Color> op = Color::brighter;
        // Image transformed = transform(image, op.compose(Color::grayscale));
        Image transformed = transform(image, compose(op, Color::grayscale));
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(transformed))));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
