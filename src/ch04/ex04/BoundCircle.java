package ch04.ex04;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class BoundCircle extends Application {
    public void start(Stage stage) {
        Circle circle = new Circle(100, 100, 100);
        circle.setFill(Color.CHARTREUSE);

        Pane pane = new Pane();
        pane.getChildren().add(circle);

        Scene scene = new Scene(pane);

        NumberBinding halfWidth = Bindings.divide(scene.widthProperty(), 2);
        NumberBinding halfHeight = Bindings.divide(scene.heightProperty(), 2);

        circle.centerXProperty().bind(halfWidth);
        circle.centerYProperty().bind(halfHeight);
        circle.radiusProperty().bind(Bindings.min(halfWidth, halfHeight));

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
