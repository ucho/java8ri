package ch04.ex09;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PlanetAnimation extends Application {

    private static final double DEFAULT_SCENE_WIDTH = 400;
    private static final double DEFAULT_SCENE_HEIGHT = 300;
    private static final double DEFAULT_PLANET_RADIUS = 20;

    @Override
    public void start(Stage stage) throws Exception {
        Circle planet = new Circle(DEFAULT_PLANET_RADIUS, Color.MEDIUMBLUE);

        Ellipse path = new Ellipse();
        path.setRadiusX((DEFAULT_SCENE_WIDTH - DEFAULT_PLANET_RADIUS * 2) / 2);
        path.setRadiusY((DEFAULT_SCENE_HEIGHT - DEFAULT_PLANET_RADIUS * 2) / 2);
        path.setCenterX(DEFAULT_SCENE_WIDTH / 2);
        path.setCenterY(DEFAULT_SCENE_HEIGHT / 2);

        PathTransition transition = new PathTransition();
        transition.setPath(path);
        transition.setNode(planet);
        transition.setDuration(Duration.seconds(5));
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.play();

        Pane pane = new Pane();
        pane.getChildren().add(planet);

        stage.setScene(new Scene(pane, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
