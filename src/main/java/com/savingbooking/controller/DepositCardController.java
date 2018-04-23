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
import com.savingbooking.model.DepositCard;
import com.savingbooking.model.SavingBook;
import com.savingbooking.service.DepositCardService;
import com.savingbooking.service.SavingBookService;
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
public class DepositCardController implements Initializable {

	@FXML
	private Button btnLogout;

	@FXML
	private Label depositCardId;

	@FXML
	private TextField depositAmount;

	@FXML
	private TextField customerName;

	@FXML
	private TextField idCard;

	@FXML
	private Button reset;

	@FXML
	private Button saveDepositCard;

	@FXML
	private TableView<DepositCard> depositCardTable;

	@FXML
	private TableColumn<DepositCard, Long> colDepositCardId;

	@FXML
	private TableColumn<DepositCard, String> colCustomerName;

	@FXML
	private TableColumn<DepositCard, String> colIdCard;

	@FXML
	private TableColumn<DepositCard, String> colDepositAmount;

	@FXML
	private TableColumn<DepositCard, Date> colCreateAt;

	@FXML
	private TableColumn<DepositCard, Boolean> colEdit;

	@FXML
	private MenuItem deleteDepositCards;

	@Lazy
	@Autowired
	private StageManager stageManager;

	@Autowired
	SavingBookService savingBookService;

	@Autowired
	DepositCardService depositCardService;

	private ObservableList<DepositCard> depositCardList = FXCollections.observableArrayList();

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
	private void saveDepositCard(ActionEvent event) {

		if (validate("Customer Name", getCustomerName(), "[a-zA-Z ' ']+")
				&& emptyValidation("Customer Name", customerName.getText().isEmpty())
				&& emptyValidation("Deposit Number", getDepositAmount() == null)) {

			if (depositCardId.getText() == null || depositCardId.getText() == "") {

				DepositCard depositCard = new DepositCard();
				depositCard.setCustomerName(getCustomerName());
				depositCard.setIdCard(getIdCard());
				depositCard.setDepositAmount(getDepositAmount());
				depositCard.setCreateAt(new Date());

				SavingBook savingBook = savingBookService.findByIdCard(getIdCard());
				depositCard.setSavingBook(savingBook);
				savingBook.setDeposit(savingBook.getDeposit() + getDepositAmount());
				savingBookService.update(savingBook);
				DepositCard newDepositCard = depositCardService.save(depositCard);

				saveAlert(newDepositCard);

			} else {
				DepositCard depositCard = depositCardService.find(Long.parseLong(depositCardId.getText()));
				depositCard.setCustomerName(getCustomerName());
				depositCard.setIdCard(getIdCard());
				depositCard.setDepositAmount(getDepositAmount());
				depositCard.setUpdateAt(new Date());
				
				SavingBook savingBook = savingBookService.findByIdCard(getIdCard());
				depositCard.setSavingBook(savingBook);
				savingBook.setDeposit(savingBook.getDeposit() + getDepositAmount());
				savingBookService.update(savingBook);
				DepositCard updatedDepositCard = depositCardService.update(depositCard);
				updateAlert(updatedDepositCard);
			}

			clearFields();
			loadDepositCardDetails();
		}

	}

	@FXML
	private void deleteDepositCards(ActionEvent event) {
		List<DepositCard> depositCards = depositCardTable.getSelectionModel().getSelectedItems();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete selected?");
		Optional<ButtonType> action = alert.showAndWait();

		if (action.get() == ButtonType.OK)
			depositCardService.deleteInBatch(depositCards);

		loadDepositCardDetails();
	}

	private void clearFields() {
		depositCardId.setText(null);
		customerName.clear();
		idCard.clear();
		depositAmount.clear();
	}

	private void saveAlert(DepositCard depositCard) {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Deposit card saved successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The deposit card with " + depositCard.getCustomerName() + " has been created and \n"
				+ " id is " + depositCard.getId() + ".");
		alert.showAndWait();
	}

	private void updateAlert(DepositCard depositCard) {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Deposit updated successfully.");
		alert.setHeaderText(null);
		alert.setContentText(
				"The deposit card with customer name " + depositCard.getCustomerName() + " has been updated.");
		alert.showAndWait();
	}

	public String getCustomerName() {
		return customerName.getText().trim();
	}

	public String getIdCard() {
		return idCard.getText().toString().trim();
	}

	public Double getDepositAmount() {
		return Double.valueOf(depositAmount.getText().trim());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		depositCardTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		setColumnProperties();

		loadDepositCardDetails();

	}

	private void setColumnProperties() {
		/*
		 * colCreateAt.setCellFactory(TextFieldTableCell.forTableColumn(new
		 * StringConverter<Date>() { String pattern = "dd/MM/yyyy"; DateTime
		 * dateFormatter = DateTime.ofPattern(pattern);
		 * 
		 * @Override public String toString(Date date) { if (date != null) {
		 * return dateFormatter.format(date); } else { return ""; } }
		 * 
		 * @Override public LocalDate fromString(String string) { if (string !=
		 * null && !string.isEmpty()) { return LocalDate.parse(string,
		 * dateFormatter); } else { return null; } } }));
		 */

		colDepositCardId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		colIdCard.setCellValueFactory(new PropertyValueFactory<>("idCard"));
		colDepositAmount.setCellValueFactory(new PropertyValueFactory<>("depositAmount"));
		colCreateAt.setCellValueFactory(new PropertyValueFactory<>("createAt"));
		colEdit.setCellFactory(cellFactory);
	}

	Callback<TableColumn<DepositCard, Boolean>, TableCell<DepositCard, Boolean>> cellFactory = new Callback<TableColumn<DepositCard, Boolean>, TableCell<DepositCard, Boolean>>() {
		@Override
		public TableCell<DepositCard, Boolean> call(final TableColumn<DepositCard, Boolean> param) {
			final TableCell<DepositCard, Boolean> cell = new TableCell<DepositCard, Boolean>() {
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
							DepositCard depositCard = getTableView().getItems().get(getIndex());
							updateDepositCard(depositCard);
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

				private void updateDepositCard(DepositCard depositCard) {
					depositCardId.setText(Long.toString(depositCard.getId()));
				}
			};
			return cell;
		}
	};

	private void loadDepositCardDetails() {
		depositCardList.clear();
		depositCardList.addAll(depositCardService.findAll());

		depositCardTable.setItems(depositCardList);
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
