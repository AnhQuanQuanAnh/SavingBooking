package com.savingbooking.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.savingbooking.config.StageManager;
import com.savingbooking.view.FxmlView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

@Controller
public class DashBoardController implements Initializable {

	@FXML
	private GridPane gridpane;

	@FXML
	private ImageView imageViewAccount;

	@FXML
	private ImageView imageViewSavingBook;

	@FXML
	private ImageView imageViewDepositCard;

	@FXML
	private ImageView imageViewWithDrawCard;

	@FXML
	private ImageView imageViewReport;

	@Lazy
	@Autowired
	private StageManager stageManager;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	@FXML
	private void GoToAccoundPage(MouseEvent event) throws IOException {
		stageManager.switchScene(FxmlView.USER);
	}

	@FXML
	private void GoToDepositPage(MouseEvent event) throws IOException {
		stageManager.switchScene(FxmlView.DEPOSITCARD);
	}

	@FXML
	private void GoToSavingBookPage(MouseEvent event) throws IOException {
		stageManager.switchScene(FxmlView.SAVINGBOOK);
	}

	@FXML
	private void GoToWithdrawCardPage(MouseEvent event) throws IOException {
		stageManager.switchScene(FxmlView.WITHDRAWCARD);
	}

	@FXML
	private void GoToReportPage(MouseEvent event) throws IOException {
		stageManager.switchScene(FxmlView.REPORT);
	}

}
