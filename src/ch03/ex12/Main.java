package ch03.ex12;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Image image = new Image("file:eiffel-tower.jpg");
        Image finalImage = LatentImage.from(image).transform(Color::brighter).transform(Color::grayscale)
                .transform((x, y, c) -> {
                    if (x < 10 || x > image.getWidth() - 10 || y < 10 || y > image.getHeight() - 10) {
                        return Color.GRAY;
                    }
                    return c;
                }).toImage();
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(finalImage))));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
