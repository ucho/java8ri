package ch04.ex08;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller extends Application implements Initializable {
    @FXML
    private Button topButton;
    @FXML
    private Button leftButton;
    @FXML
    private Button centerButton;
    @FXML
    private Button rightButton;
    @FXML
    private Button bottomButton;

    public void initialize(URL url, ResourceBundle rb) {
        topButton.setOnMouseClicked(e -> toggleBorder(topButton, centerButton));
        leftButton.setOnMouseClicked(e -> toggleBorder(leftButton, centerButton));
        centerButton
                .setOnMouseClicked(e -> toggleBorder(topButton, leftButton, centerButton, rightButton, bottomButton));
        rightButton.setOnMouseClicked(e -> toggleBorder(centerButton, rightButton));
        bottomButton.setOnMouseClicked(e -> toggleBorder(centerButton, bottomButton));
    }

    private Border border = new Border(new BorderStroke(Color.BLUEVIOLET, BorderStrokeStyle.SOLID, null, null));

    private void toggleBorder(Button... buttons) {
        for (Button button : buttons) {
            if (button.getBorder() == null) {
                button.setBorder(border);
            } else {
                button.setBorder(null);
            }
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
