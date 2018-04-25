package com.savingbooking.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.savingbooking.config.StageManager;
import com.savingbooking.view.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

@Controller
public class ReportController implements Initializable {

	final static String time1 = "1/2018";
	final static String time2 = "2/2018";
	final static String time3 = "3/2018";
	final static String time4 = "4/2018";
	final static String time5 = "5/2018";

	@FXML
	private Button btnLogout;

	@FXML
	private BarChart barchard;

	@FXML
	private PieChart piechart;
	@Lazy
	@Autowired
	private StageManager stageManager;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// barchart process
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		barchard.setTitle("WithDraw-Cart Report");

		xAxis.setLabel("Time");
		yAxis.setLabel("VND");

		XYChart.Series series1 = new XYChart.Series();

		series1.setName("3/2018");
		series1.getData().add(new XYChart.Data(time1, 25601.34));
		series1.getData().add(new XYChart.Data(time2, 20148.82));
		series1.getData().add(new XYChart.Data(time3, 10000));
		series1.getData().add(new XYChart.Data(time4, 35407.15));
		series1.getData().add(new XYChart.Data(time5, 12000));

		// piechart process
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Grapefruit", 13), new PieChart.Data("Oranges", 25), new PieChart.Data("Plums", 10),
				new PieChart.Data("Pears", 22), new PieChart.Data("Apples", 30));
		piechart = new PieChart(pieChartData);
		piechart.setTitle("Present of withdrawing");
	}

	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	private void logout(ActionEvent event) throws IOException {
		stageManager.switchScene(FxmlView.LOGIN);
	}

}
