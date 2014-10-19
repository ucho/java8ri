package ch04.ex10;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class SimpleWebBrowser extends Application {

    private static final String DEFAULT_LOCATION = "http://horstmann.com";

    @Override
    public void start(Stage stage) {
        WebView browser = new WebView();
        WebEngine engine = browser.getEngine();

        Button backButton = createBackButton(engine);
        TextField locationBar = createLocationBar(engine, DEFAULT_LOCATION);

        HBox controls = new HBox();
        HBox.setHgrow(locationBar, Priority.ALWAYS);
        controls.getChildren().addAll(backButton, locationBar);

        VBox pane = new VBox();
        pane.getChildren().addAll(controls, browser);

        engine.load(DEFAULT_LOCATION);

        stage.setScene(new Scene(pane));
        stage.setWidth(500);
        stage.setHeight(500);
        stage.show();
    }

    private Button createBackButton(WebEngine engine) {
        Button button = new Button("Back");
        WebHistory history = engine.getHistory();
        button.disableProperty().bind(history.currentIndexProperty().isEqualTo(0));
        button.setOnMouseClicked(e -> {
            if (history.getCurrentIndex() != 0) {
                engine.getHistory().go(-1);
            }
        });
        return button;
    }

    private TextField createLocationBar(WebEngine engine, String defaultLocation) {
        TextField bar = new TextField(defaultLocation);
        bar.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                engine.load(bar.textProperty().get());
            }
        });
        engine.locationProperty().addListener((p, o, n) -> {
            bar.textProperty().set(n);
        });
        return bar;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
