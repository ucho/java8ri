package ch04.ex01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloWorld extends Application {

    public void start(Stage stage) {
        String message = "Hello, FX";
        Label label = new Label(message);
        label.setFont(new Font(100));

        TextField field = new TextField(message);
        label.textProperty().bind(field.textProperty());

        VBox pane = new VBox();
        pane.getChildren().addAll(label, field);
        stage.setScene(new Scene(pane));

        stage.setTitle("Hello");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
