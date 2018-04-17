package com.savingbooking.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.savingbooking.model.TypeOfSavingBook;
import com.savingbooking.service.SavingBookService;
import com.savingbooking.service.TypeOfSavingBookService;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.util.StringConverter;

@Controller
public class SavingBookController implements Initializable {

	@FXML
	private Button btnLogout;

	@FXML
	private Label savingbookId;

	@FXML
	private TextField firstName;

	@FXML
	private TextField lastName;

	@FXML
	private DatePicker dob;

	@FXML
	private TextField phoneNumber;

	@FXML
	private TextField idCard;

	@FXML
	private TextField address;

	@FXML
	private TextField depositNumber;

	@FXML
	private RadioButton rbMale;

	@FXML
	private ToggleGroup gender;

	@FXML
	private RadioButton rbFemale;

	@FXML
	private TextField email;

	@FXML
	private ComboBox<TypeOfSavingBook> cbTypeOfSavingBook;

	@FXML
	private Button reset;

	@FXML
	private Button saveSavingBook;

	@FXML
	private TableView<SavingBook> savingbookTable;

	@FXML
	private TableColumn<SavingBook, Long> colSavingBookId;

	@FXML
	private TableColumn<SavingBook, String> colFirstName;

	@FXML
	private TableColumn<SavingBook, String> colLastName;

	@FXML
	private TableColumn<SavingBook, LocalDate> colDOB;

	@FXML
	private TableColumn<SavingBook, String> colGender;

	@FXML
	private TableColumn<SavingBook, String> colPhoneNumber;

	@FXML
	private TableColumn<SavingBook, String> colIdCard;

	@FXML
	private TableColumn<SavingBook, String> colAddress;

	@FXML
	private TableColumn<SavingBook, String> colEmail;

	@FXML
	private TableColumn<SavingBook, String> colDepositNumber;

	@FXML
	private TableColumn<TypeOfSavingBook, String> colTypeOfSavingBook;

	@FXML
	private TableColumn<SavingBook, Boolean> colEdit;

	@FXML
	private MenuItem deleteSavingBook;

	@Lazy
	@Autowired
	private StageManager stageManager;

	@Autowired
	private SavingBookService savingBookService;
	@Autowired
	private TypeOfSavingBookService typeOfSavingBookService;

	private ObservableList<SavingBook> savingbookList = FXCollections.observableArrayList();

	private ObservableList<TypeOfSavingBook> typeOfSavingBookList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		typeOfSavingBookList = FXCollections.observableArrayList();
		for (TypeOfSavingBook item : typeOfSavingBookService.findAll()) {
			typeOfSavingBookList.add(item);
		}
		cbTypeOfSavingBook.setItems(typeOfSavingBookList);

		savingbookTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		setColumnProperties();

		loadSavingBookDetails();
	}

	private void setColumnProperties() {
		colDOB.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<LocalDate>() {
			String pattern = "dd/MM/yyyy";
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return dateFormatter.format(date);
				} else {
					return "";
				}
			}

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				} else {
					return null;
				}
			}
		}));
		colSavingBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
		colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		colIdCard.setCellValueFactory(new PropertyValueFactory<>("idCard"));
		colDepositNumber.setCellValueFactory(new PropertyValueFactory<>("deposit"));
		colTypeOfSavingBook.setCellValueFactory(new PropertyValueFactory<TypeOfSavingBook, String>("typeOfSavingBook"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colEdit.setCellFactory(cellFactory);
	}

	Callback<TableColumn<SavingBook, Boolean>, TableCell<SavingBook, Boolean>> cellFactory = new Callback<TableColumn<SavingBook, Boolean>, TableCell<SavingBook, Boolean>>() {
		@Override
		public TableCell<SavingBook, Boolean> call(final TableColumn<SavingBook, Boolean> param) {
			final TableCell<SavingBook, Boolean> cell = new TableCell<SavingBook, Boolean>() {
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
							SavingBook savingbook = getTableView().getItems().get(getIndex());
							updateSavingBook(savingbook);
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

				private void updateSavingBook(SavingBook savingbook) {
					savingbookId.setText(Long.toString(savingbook.getId()));
					firstName.setText(savingbook.getFirstName());
					lastName.setText(savingbook.getLastName());
					dob.setValue(savingbook.getDob());
					email.setText(savingbook.getEmail());
					phoneNumber.setText(savingbook.getPhoneNumber());
					address.setText(savingbook.getAddress());
					idCard.setText(savingbook.getIdCard());
					depositNumber.setText(String.valueOf(savingbook.getDeposit()));
					if (savingbook.getGender().equals("Male"))
						rbMale.setSelected(true);
					else
						rbFemale.setSelected(true);
					cbTypeOfSavingBook.getSelectionModel().select(getTypeOfSavingBook());
				}
			};
			return cell;
		}
	};

	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	private void logout(ActionEvent event) throws IOException {
		stageManager.switchScene(FxmlView.LOGIN);
	}

	@FXML
	void reset(ActionEvent event) {
		clearFields();
	}

	private void clearFields() {
		savingbookId.setText(null);
		firstName.clear();
		lastName.clear();
		dob.getEditor().clear();
		rbMale.setSelected(true);
		rbFemale.setSelected(false);
		email.clear();
		idCard.clear();
		phoneNumber.clear();
		depositNumber.clear();
		address.clear();
		cbTypeOfSavingBook.getSelectionModel().clearSelection();
	}

	public String getFirstName() {
		return firstName.getText().trim();
	}

	public String getLastName() {
		return lastName.getText().trim();
	}

	public LocalDate getDob() {
		return dob.getValue();
	}

	public String getGender() {
		return rbMale.isSelected() ? "Male" : "Female";
	}

	public String getEmail() {
		return email.getText().trim();
	}

	public String getPhoneNumber() {
		return phoneNumber.getText().trim();
	}

	public String getIdCard() {
		return idCard.getText().trim();
	}

	public String getAddress() {
		return address.getText().trim();
	}

	public Double getDepositNumber() {
		return Double.parseDouble(depositNumber.getText().trim());
	}

	public TypeOfSavingBook getTypeOfSavingBook() {
		return cbTypeOfSavingBook.getValue();
	}

	private String getGenderTitle(String gender) {
		return (gender.equals("Male")) ? "his" : "her";
	}

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
		if (field.equals("Role"))
			alert.setContentText("Please Select " + field);
		else {
			if (empty)
				alert.setContentText("Please Enter " + field);
			else
				alert.setContentText("Please Enter Valid " + field);
		}
		alert.showAndWait();
	}

	private void saveAlert(SavingBook savingBook) {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("SavingBook saved successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The savingbook of " + savingBook.getFirstName() + " " + savingBook.getLastName()
				+ " has been created and \n" + getGenderTitle(savingBook.getGender()) + " id is " + savingBook.getId()
				+ ".");
		alert.showAndWait();
	}

	private void updateAlert(SavingBook savingBook) {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("SavingBook updated successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The savingbook of " + savingBook.getFirstName() + " " + savingBook.getLastName()
				+ " has been updated.");
		alert.showAndWait();
	}

	@FXML
	private void saveSavingBook(ActionEvent event) {
		if (validate("First Name", getFirstName(), "[a-zA-Z]+") && validate("Last Name", getLastName(), "[a-zA-Z]+")
				&& emptyValidation("DOB", dob.getEditor().getText().isEmpty())) {

			if (savingbookId.getText() == null || savingbookId.getText() == "") {
				if (validate("Email", getEmail(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")) {

					SavingBook savingBook = new SavingBook();
					savingBook.setFirstName(getFirstName());
					savingBook.setLastName(getLastName());
					savingBook.setDob(getDob());
					savingBook.setGender(getGender());
					savingBook.setEmail(getEmail());
					savingBook.setPhoneNumber(getPhoneNumber());
					savingBook.setIdCard(getIdCard());
					savingBook.setAddress(getAddress());
					savingBook.setCreatedAt(new Date());
					savingBook.setDeposit(getDepositNumber());
					savingBook.setTypeOfSavingBook(getTypeOfSavingBook());
					SavingBook newSavingBook = savingBookService.save(savingBook);

					saveAlert(newSavingBook);
				}

			} else {
				SavingBook savingBook = savingBookService.find(Long.parseLong(savingbookId.getText()));
				savingBook.setFirstName(getFirstName());
				savingBook.setLastName(getLastName());
				savingBook.setDob(getDob());
				savingBook.setGender(getGender());
				savingBook.setUpdateAt(new Date());
				SavingBook updatedSavingBook = savingBookService.update(savingBook);
				updateAlert(updatedSavingBook);
			}

			clearFields();
			loadSavingBookDetails();
		}

	}

	@FXML
	private void deleteSavingBook(ActionEvent event) {
		List<SavingBook> savingBooks = savingbookTable.getSelectionModel().getSelectedItems();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete selected?");
		Optional<ButtonType> action = alert.showAndWait();

		if (action.get() == ButtonType.OK)
			savingBookService.deleteInBatch(savingBooks);

		loadSavingBookDetails();
	}

	private void loadSavingBookDetails() {
		savingbookList.clear();
		savingbookList.addAll(savingBookService.findAll());
		savingbookTable.setItems(savingbookList);
	}

}
