package ch03.ex15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Image image = new Image("file:eiffel-tower.jpg");
        Image finalImage = LatentImage.from(image).transform(Color::brighter).transform(Color::grayscale)
                .toImageInParallel();
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(finalImage))));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
