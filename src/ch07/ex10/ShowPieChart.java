package ch07.ex10;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class ShowPieChart extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        List<String> lines = Files.readAllLines(Paths.get("population_of_the_continents.txt"));
        lines.stream().map(line -> {
            String[] values = line.split(",");
            return new PieChart.Data(values[0], Double.parseDouble(values[1]));
        }).forEach(data -> pieChartData.add(data));

        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Population of the Continents");
        stage.setScene(new Scene(chart));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
