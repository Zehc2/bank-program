package com.bank.engine.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.bank.engine.accounts.Account;
import com.bank.engine.accounts.AccountType;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginSceneController extends Controller {

	@FXML
	Label errorMessage;
	@FXML
	TextField passwordField;
	private Parent root;

	private Scene scene;
	private Stage stage;
	@FXML
	TextField usernameTextField;

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		this.errorMessage.setVisible(false);
	}

	public void login(final ActionEvent event) throws IOException {

		try {
			final String username = this.usernameTextField.getText();
			final String password = this.passwordField.getText();

			Account account;

			final Account tempAccount = this.bank.createNewTempAccount(username, password);
			final AccountType accountType = this.bank.getAccountType(tempAccount);

			if (accountType == AccountType.INVESTMENT) {
				account = this.bank.createNewInvestmentAccount(username, password);
			} else if (accountType == AccountType.SAVINGS) {
				account = this.bank.createNewSavingsAccount(username, password);
			} else {
				account = this.bank.createNewCheckingAccount(username, password);
			}

			this.setAccount(account);

			if (this.bank.login(Controller.getAccount()) && !username.isEmpty() && !password.isEmpty()) {
				this.LOGGER.success("Switched to account scene");
				this.setAccount(account);
				final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AccountScene.fxml"));
				this.root = loader.load();
				this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				this.scene = new Scene(this.root);
				this.stage.setScene(this.scene);
				this.stage.show();
			} else {
				this.LOGGER.warn("Invalid username or password");
				this.errorMessage.setVisible(true);
			}
		} catch (final NullPointerException e) {
			this.LOGGER.warn("Invalid account. Exception: " + e);
			this.errorMessage.setVisible(true);
		}

	}
}
