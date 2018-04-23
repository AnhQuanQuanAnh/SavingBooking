package com.savingbooking.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.savingbooking.config.StageManager;
import com.savingbooking.model.SavingBook;
import com.savingbooking.model.WithdrawCard;
import com.savingbooking.service.SavingBookService;
import com.savingbooking.service.WithdrawCardService;
import com.savingbooking.view.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

@Controller
public class WithdrawCardController implements Initializable {

	@FXML
	private Button btnLogout;

	@FXML
	private Label withdrawCardId;

	@FXML
	private TextField withdrawAmount;

	@FXML
	private TextField customerName;

	@FXML
	private TextField idCard;

	@FXML
	private Button reset;

	@FXML
	private Button saveWithdrawCard;

	@FXML
	private TableView<WithdrawCard> withdrawCardTable;

	@FXML
	private TableColumn<WithdrawCard, Long> colWithdrawCardId;

	@FXML
	private TableColumn<WithdrawCard, String> colCustomerName;

	@FXML
	private TableColumn<WithdrawCard, String> colIdCard;

	@FXML
	private TableColumn<WithdrawCard, String> colWithdrawAmount;

	@FXML
	private TableColumn<WithdrawCard, Date> colCreateAt;

	@FXML
	private TableColumn<WithdrawCard, Boolean> colEdit;

	@FXML
	private MenuItem deleteWithdrawCards;

	@Lazy
	@Autowired
	private StageManager stageManager;

	@Autowired
	SavingBookService savingBookService;

	@Autowired
	WithdrawCardService withdrawCardService;

	private ObservableList<WithdrawCard> withdrawCardList = FXCollections.observableArrayList();

	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
	}

	/**
	 * Logout and go to the login page
	 */
	@FXML
	private void logout(ActionEvent event) throws IOException {
		stageManager.switchScene(FxmlView.LOGIN);
	}

	@FXML
	void reset(ActionEvent event) {
		clearFields();
	}

	@FXML
	private void saveWithdrawCard(ActionEvent event) {

		if (validate("Customer Name", getCustomerName(), "[a-zA-Z ' ']+")
				&& emptyValidation("Customer Name", customerName.getText().isEmpty())
				&& emptyValidation("Withdraw Number", getWithdrawAmount() == null)) {

			if (withdrawCardId.getText() == null || withdrawCardId.getText() == "") {

				WithdrawCard withdrawCard = new WithdrawCard();
				withdrawCard.setCustomerName(getCustomerName());
				withdrawCard.setIdCard(getIdCard());
				withdrawCard.setWithdrawAmount(getWithdrawAmount());
				withdrawCard.setCreateAt(new Date());

				SavingBook savingBook = savingBookService.findByIdCard(getIdCard());
				if (savingBook == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Id card is not registered on the system");
					alert.showAndWait();
				}
				withdrawCard.setSavingBook(savingBook);
				savingBook.setDeposit(savingBook.getDeposit() - getWithdrawAmount());
				savingBookService.update(savingBook);
				WithdrawCard newWithdrawCard = withdrawCardService.save(withdrawCard);

				saveAlert(newWithdrawCard);

			} else {
				WithdrawCard withdrawCard = withdrawCardService.find(Long.parseLong(withdrawCardId.getText()));
				withdrawCard.setCustomerName(getCustomerName());
				withdrawCard.setIdCard(getIdCard());
				withdrawCard.setWithdrawAmount(getWithdrawAmount());
				withdrawCard.setUpdateAt(new Date());

				SavingBook savingBook = savingBookService.findByIdCard(getIdCard());
				if (savingBook == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Id card is not registered on the system");
					alert.showAndWait();
				}
				withdrawCard.setSavingBook(savingBook);
				savingBook.setDeposit(savingBook.getDeposit() - getWithdrawAmount());
				savingBookService.update(savingBook);
				WithdrawCard updatedWithdrawCard = withdrawCardService.update(withdrawCard);
				updateAlert(updatedWithdrawCard);
			}

			clearFields();
			loadWithdrawCardDetails();
		}

	}

	@FXML
	private void deleteWithdrawCards(ActionEvent event) {
		List<WithdrawCard> withdrawCards = withdrawCardTable.getSelectionModel().getSelectedItems();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete selected?");
		Optional<ButtonType> action = alert.showAndWait();

		if (action.get() == ButtonType.OK)
			withdrawCardService.deleteInBatch(withdrawCards);

		loadWithdrawCardDetails();
	}

	private void clearFields() {
		withdrawCardId.setText(null);
		customerName.clear();
		idCard.clear();
		withdrawAmount.clear();
	}

	private void saveAlert(WithdrawCard withdrawCard) {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Withdraw card saved successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The withdraw card with " + withdrawCard.getCustomerName() + " has been created and \n"
				+ " id is " + withdrawCard.getId() + ".");
		alert.showAndWait();
	}

	private void updateAlert(WithdrawCard withdrawCard) {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Withdraw card updated successfully.");
		alert.setHeaderText(null);
		alert.setContentText(
				"The withdraw card with customer name " + withdrawCard.getCustomerName() + " has been updated.");
		alert.showAndWait();
	}

	public String getCustomerName() {
		return customerName.getText().trim();
	}

	public String getIdCard() {
		return idCard.getText().toString().trim();
	}

	public Double getWithdrawAmount() {
		return Double.valueOf(withdrawAmount.getText().trim());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		withdrawCardTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		setColumnProperties();

		loadWithdrawCardDetails();

	}

	private void setColumnProperties() {

		colWithdrawCardId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		colIdCard.setCellValueFactory(new PropertyValueFactory<>("idCard"));
		colWithdrawAmount.setCellValueFactory(new PropertyValueFactory<>("withdrawAmount"));
		colCreateAt.setCellValueFactory(new PropertyValueFactory<>("createAt"));
		colEdit.setCellFactory(cellFactory);
	}

	Callback<TableColumn<WithdrawCard, Boolean>, TableCell<WithdrawCard, Boolean>> cellFactory = new Callback<TableColumn<WithdrawCard, Boolean>, TableCell<WithdrawCard, Boolean>>() {
		@Override
		public TableCell<WithdrawCard, Boolean> call(final TableColumn<WithdrawCard, Boolean> param) {
			final TableCell<WithdrawCard, Boolean> cell = new TableCell<WithdrawCard, Boolean>() {
				Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
				final Button btnEdit = new Button();

				@Override
				public void updateItem(Boolean check, boolean empty) {
					super.updateItem(check, empty);
					if (empty) {
						setGraphic(null);
						setText(null);
					} else {
						btnEdit.setOnAction(e -> {
							WithdrawCard withdrawCard = getTableView().getItems().get(getIndex());
							updateWithdrawCard(withdrawCard);
						});

						btnEdit.setStyle("-fx-background-color: transparent;");
						ImageView iv = new ImageView();
						iv.setImage(imgEdit);
						iv.setPreserveRatio(true);
						iv.setSmooth(true);
						iv.setCache(true);
						btnEdit.setGraphic(iv);
						setGraphic(btnEdit);
						setAlignment(Pos.CENTER);
						setText(null);
					}
				}

				private void updateWithdrawCard(WithdrawCard withdrawCard) {
					withdrawCardId.setText(Long.toString(withdrawCard.getId()));
					customerName.setText(withdrawCard.getCustomerName());
					idCard.setText(withdrawCard.getIdCard());
					withdrawAmount.setText(String.valueOf(withdrawCard.getWithdrawAmount()));
				}
			};
			return cell;
		}
	};

	private void loadWithdrawCardDetails() {
		withdrawCardList.clear();
		withdrawCardList.addAll(withdrawCardService.findAll());

		withdrawCardTable.setItems(withdrawCardList);
	}

	/*
	 * Validations
	 */
	private boolean validate(String field, String value, String pattern) {
		if (!value.isEmpty()) {
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(value);
			if (m.find() && m.group().equals(value)) {
				return true;
			} else {
				validationAlert(field, false);
				return false;
			}
		} else {
			validationAlert(field, true);
			return false;
		}
	}

	private boolean emptyValidation(String field, boolean empty) {
		if (!empty) {
			return true;
		} else {
			validationAlert(field, true);
			return false;
		}
	}

	private void validationAlert(String field, boolean empty) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Validation Error");
		alert.setHeaderText(null);
		if (empty)
			alert.setContentText("Please Enter " + field);
		else
			alert.setContentText("Please Enter Valid " + field);

		alert.showAndWait();
	}
}
