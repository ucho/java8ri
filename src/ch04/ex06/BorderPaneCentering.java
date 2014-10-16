package ch04.ex06;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BorderPaneCentering extends Application {
    public void start(Stage stage) {
        BorderPane pane = new BorderPane();

        HBox topPane = new HBox(new Button("Top"));
        topPane.setAlignment(Pos.CENTER);
        pane.setTop(topPane);

        pane.setLeft(new Button("Left"));
        pane.setCenter(new Button("Center"));
        pane.setRight(new Button("Right"));

        HBox bottomPane = new HBox(new Button("Bottom"));
        bottomPane.setAlignment(Pos.CENTER);
        pane.setBottom(bottomPane);

        stage.setScene(new Scene(pane));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}