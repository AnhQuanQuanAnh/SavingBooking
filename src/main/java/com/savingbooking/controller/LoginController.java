package com.savingbooking.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.savingbooking.config.StageManager;
import com.savingbooking.model.User;
import com.savingbooking.service.UserService;
import com.savingbooking.view.FxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

@Controller
public class LoginController implements Initializable {

	@FXML
	private Button btnLogin;

	@FXML
	private PasswordField password;

	@FXML
	private TextField username;

	@FXML
	private Label lblLogin;

	@Autowired
	UserService userService;

	@Lazy
	@Autowired
	private StageManager stageManager;

	@FXML
	private void login(ActionEvent event) throws IOException {
		if (userService.authenticate(getUsername(), getPassword())) {

			User user = userService.findByEmail(getUsername());
			if (user.getRole().equals("User")) {
				stageManager.switchScene(FxmlView.DASHBOARD);
			} else {
				stageManager.switchScene(FxmlView.USER);
			}

		} else {
			lblLogin.setText("Login Failed.");
		}
	}

	public String getPassword() {
		return password.getText().trim();
	}

	public String getUsername() {
		return username.getText().trim();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
