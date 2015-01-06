#!/usr/bin/jjs -fx

// import
var Double = java.lang.Double;
var Files = java.nio.file.Files;
var Paths = java.nio.file.Paths;
var List = java.util.List;

var Application = javafx.application.Application;
var FXCollections = javafx.collections.FXCollections;
var ObservableList = javafx.collections.ObservableList;
var PieChart = javafx.scene.chart.PieChart;
var Scene = javafx.scene.Scene;

// start method
var pieChartData = FXCollections.observableArrayList();
var lines = Files.readAllLines(Paths.get("population_of_the_continents.txt"));
lines.stream().map(function(line) {
    var values = line.split(",");
    return new PieChart.Data(values[0], Double.parseDouble(values[1]));
}).forEach(function(data) {
    pieChartData.add(data);
});

var chart = new PieChart(pieChartData);
chart.title = "Population of the Continents";
$STAGE.scene = new Scene(chart);
